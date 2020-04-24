package com.zhancheng.agent.applet.service;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxPayService
 * @date 2019/11/8 17:36
 */
public interface WxPayService {

   Boolean balancePay(Integer orderId, Integer agentId);

   Map<String, String> agentWxPay(Integer orderId, Integer agentId) throws Exception;

   /**
    * 微信支付回调
    * @param notifyXml 回调信息
    * @return
    */
   String wxPayUnifiedNotify(String notifyXml);
}
