package com.zhancheng.retail.service.impl;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.util.WxUtil;
import com.zhancheng.retail.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public Map<String, Object> wxLogin(String code,Integer superiorId){

        Map<String, Object> map = new HashMap<>(2);

        // 获取accessToken
        JSONObject json = wxUtil.getAccessToken(code);

        String openId = json.getStr("openid");
        String accessToken = json.getStr("access_token");

        JSONObject jsonObject = wxUtil.getUserInfo(openId, accessToken);
        System.err.println(openId);
        User user= userMapper.selectOne(new QueryWrapper<User>()
                .eq("openid", openId)
                .eq("is_deleted", 0));
        System.err.println(user);
        // 查询代理信息
        Agent agent = agentMapper.selectOne(new QueryWrapper<Agent>().eq("openid", openId));

        if (ObjectUtil.isNotNull(user)) {
            user.setGmtLogin(new Date());
            user.updateById();

            String token = redisTemplate.setUser(user);

            if (ObjectUtil.isNotNull(agent)) {
                map.put("agent", agent);
            }
            map.put("token", token);
            map.put("user", user);
        }else {
            User userInfo = new User();
            userInfo.setNickname(jsonObject.getStr("nickname"));
            userInfo.setOpenid(jsonObject.getStr("openid"));
            userInfo.setGender(jsonObject.getInt("sex"));
            userInfo.setRegion(jsonObject.getStr("province") + jsonObject.getStr("city"));
            userInfo.setStatus(1);
            userInfo.setCover(jsonObject.getStr("headimgurl"));
            // 上级id
            userInfo.setSuperior(superiorId);
            userInfo.setGmtLogin(new Date());
            userMapper.insert(userInfo);
            String token = redisTemplate.setUser(userInfo);

            if (ObjectUtil.isNotNull(agent)) {
                agent.setUserId(userInfo.getUid());
                map.put("agent", agent);
                agent.updateById();
            }
            map.put("user", userInfo);
            map.put("token", token);
        }
        return map;
    }
}
