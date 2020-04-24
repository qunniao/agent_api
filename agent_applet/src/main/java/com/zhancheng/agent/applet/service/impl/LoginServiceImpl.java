package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.LoginService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.GlobalExceptionHandler;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.AgentRegisterReviewMapper;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginServiceImpl
 * @date 2019/10/26 11:51
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WxUtil wxUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentRegisterReviewMapper agentRegisterReviewMapper;

    private Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public Map<String, Object> wxLogin(String code) {

        Map<String, Object> map = new HashMap<>(2);

        // 获取accessToken
        JSONObject json = wxUtil.getAccessToken(code);

        System.err.println(json);

        String openId = json.getStr("openid");

        Agent agent = agentMapper.selectOne(new QueryWrapper<Agent>().eq("openid", openId));

        // 未绑定微信跳转到账号密码登录
        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.WECHAT_IS_UNBOUND);
        } else {
            AgentLevel agentLevel = agentLevelMapper.selectOne(new QueryWrapper<AgentLevel>().eq("id", agent.getLevelId()));
            map.put("agent", agent);
            map.put("agentLevel", agentLevel);

            if (agent.getReviewed() == 0) {
                AgentRegisterReview agentRegisterReview = agentRegisterReviewMapper
                        .selectOne(new QueryWrapper<AgentRegisterReview>().eq("agent_id", agent.getId()));
                map.put("agentRegisterReview", agentRegisterReview);
            }
        }

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> accountLogin(String phone, String password, String code) {

        // 对密码进行加密
        String pwd = SecureUtil.md5(password);

        Agent agent = agentMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", phone).eq("password", pwd));

        Map<String, Object> map = new HashMap<>(2);

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.PASSWORD_ERROR);
        } else {

            // 获取accessToken
            JSONObject json = wxUtil.getAccessToken(code);
            String openId = json.getStr("openid");
            String accessToken = json.getStr("access_token");

            JSONObject jsonObject = wxUtil.getUserInfo(openId, accessToken);
            log.info("code获取用户信息{}", jsonObject);
            agent.setNickname(jsonObject.getStr("nickname"))
                    .setOpenid(openId)
                    .setGender(jsonObject.getInt("sex"))
                    .setCover(jsonObject.getStr("headimgurl"));

            User user = userMapper.selectOne(new QueryWrapper<User>().eq("openid", openId));

            if (ObjectUtil.isNotNull(user)) {
                agent.setUserId(user.getUid());
            }
            agent.updateById();

            AgentLevel agentLevel = agentLevelMapper.selectOne(new QueryWrapper<AgentLevel>().eq("id", agent.getLevelId()));

            map.put("agent", agent);
            map.put("agentLevel", agentLevel);
        }

        return map;
    }

    @Override
    public Map<String, Object> phoneLogin(String phone, String password, String code) {

        Map<String, Object> map = new HashMap<>(2);

        // 获取验证码
        String phoneCode = redisTemplate.getKey(phone);

        // 验证码已失效
        if (StrUtil.isBlank(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_IS_NULL);
        }

        if (!code.equals(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_ERROR);
        }

        Agent agent = agentMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", phone).eq("password", SecureUtil.md5(password)));

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.PASSWORD_ERROR);
        }

        AgentLevel agentLevel = agentLevelMapper.selectOne(new QueryWrapper<AgentLevel>().eq("id", agent.getLevelId()));

        map.put("agent", agent);
        map.put("agentLevel", agentLevel);

        return map;
    }

    @Override
    public Boolean weChatUntie(Integer agentId) {

        Agent agent = agentMapper.queryById(agentId);

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        }

        agent.setOpenid("");

        return agent.updateById();
    }
}
