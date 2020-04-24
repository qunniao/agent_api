package com.zhancheng.retail.service;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginService
 * @date 2019/10/26 11:51
 */
public interface LoginService {

    /**
     * 用户登录
     * @param code
     * @return
     * @throws Exception
     */
    Map<String, Object> wxLogin(String code, Integer superiorId);
}
