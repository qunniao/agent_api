package com.zhancheng.core.config.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;

import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.Admin;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.User;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by tangchao on 2018/1/26  17:08
 */
@Aspect
@Component
public class MyAspect {

    @Autowired
    private HttpServletRequest request;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AgentMapper agentMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在所有标注@Verify的地方切入
     *
     * @param joinPoint 切入点
     */
    @Before("@annotation(com.zhancheng.core.config.security.Verify)")
    public void beforeExec(JoinPoint joinPoint) {
        logger.info("**************************************");
        logger.info(getMethodName(joinPoint) + ":start");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String role = method.getAnnotation(Verify.class).role();
        // 判断是否要验证权限
        if (ObjectUtil.notEqual(role, UserIdentity.NOT)) {

            // 从请求中获取token
            String token = request.getHeader("token");

            //判断token是否为空
            if (StrUtil.isBlank(token)) {
                throw new MyException(CodeMsg.TOKEN_IS_NULL);
            }
            JSONObject jsonObject;

            // 判断身份
            switch (role) {
                // 管理员
                case UserIdentity.ADMIN :{

                    jsonObject = redisTemplate.getAdmin(token);

                    if (ObjectUtil.isNull(jsonObject)) {
                        throw new MyException((CodeMsg.TO_KENG_ABNORMAL));
                    }
                    Admin admin = adminMapper.selectById(jsonObject.getInt("uid"));
                    if (ObjectUtil.isNull(admin)) {
                        throw new MyException(CodeMsg.USER_NOT_EXISTED);
                    }
                    if (admin.getStatus() == 0){
                        throw new MyException(CodeMsg.FROZEN_ACCOUNT);
                    }
                }
                break;
                // 普通用户
                case UserIdentity.ORDINARY :{
                    jsonObject = redisTemplate.getUser(token);
                    if (jsonObject == null) {
                        throw new MyException((CodeMsg.TO_KENG_ABNORMAL));
                    }
                    User user = userMapper.selectById(jsonObject.getInt("uid"));
                    if (ObjectUtil.isNull(user)) {
                        throw new MyException(CodeMsg.USER_NOT_EXISTED);
                    }
                    if (user.getStatus() == 0){
                        throw new MyException(CodeMsg.FROZEN_ACCOUNT);
                    }
                }
                break;
                // 代理
                case UserIdentity.AGENT :{
                    jsonObject = redisTemplate.getAgent(token);
                    if (jsonObject == null) {
                        throw new MyException((CodeMsg.TO_KENG_ABNORMAL));
                    }
                   Agent agent = agentMapper.selectById(jsonObject.getInt("uid"));
                    if (ObjectUtil.isNull(agent)) {
                        throw new MyException(CodeMsg.USER_NOT_EXISTED);
                    }

                    if (agent.getStatus() == 0){
                        throw new MyException(CodeMsg.AGENT_FROZEN_ACCOUNT);
                    }
                }
                break;
                case UserIdentity.NOT :
                default:
                    break;
            }
        }
    }

    /**
     * 正常返回
     *
     * @param joinPoint
     */
    @AfterReturning(value = "@annotation(com.zhancheng.core.config.security.Verify)")
    public void after(JoinPoint joinPoint) {
        logger.info(getMethodName(joinPoint) + ":end");
        logger.info("**************************************");
    }


    /**
     * 异常返回
     *
     * @param
     */
    @AfterThrowing(value = "@annotation(com.zhancheng.core.config.security.Verify)", throwing = "ex")
    public void afterEx(Exception ex) {
        ex.printStackTrace();

        logger.error(ex.getMessage());
        logger.error("**************************************");
    }

    /**
     * 获得请求方法的路径
     *
     * @param joinPoint 传入JoinPoin
     * @return 截取类名+方法名
     */
    public static String getMethodName(JoinPoint joinPoint) {
        String str = joinPoint.getSignature().toString();
        int i = str.lastIndexOf(".");
        i = str.substring(0, i).lastIndexOf(".");
        String result = str.substring(i + 1);
        return result;
    }
}
