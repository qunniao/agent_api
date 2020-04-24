package com.zhancheng.agent.applet.service;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginService
 * @date 2019/10/26 11:51
 */
public interface LoginService {

    /**
     * 用户微信登录
     * @param code 微信 code
     * @return
     * @throws Exception
     */
    Map<String, Object> wxLogin(String code)throws Exception ;
    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @param code 用户授权
     * @return Map<String, Object>
     */
    Map<String, Object> accountLogin(String phone, String password, String code);

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @param code 手机号验证码
     * @return Map<String, Object>
     */
    Map<String, Object> phoneLogin(String phone, String password, String code);

    /**
     * 用户登录
     * @param agentId 代理id
     * @return Boolean
     */
    Boolean weChatUntie(Integer agentId);
}
