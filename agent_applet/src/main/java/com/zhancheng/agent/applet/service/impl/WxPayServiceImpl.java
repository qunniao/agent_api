package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhancheng.agent.applet.service.WxPayService;
import com.zhancheng.core.config.exception.GlobalExceptionHandler;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.wx.WxConfig;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.WxPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxPayServiceImpl
 * @date 2019/11/8 17:37
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private RewardMapper rewardMapper;

    @Resource
    private AgentWalletMapper agentWalletMapper;

    @Resource
    private WxPayUtil wxPayUtil;

    @Resource
    private WxConfig wxConfig;

    private static Logger log = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean balancePay(Integer orderId, Integer agentId) {

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);

        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        Agent agent = agentMapper.selectById(agentId);

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        }

        // 查询付款代理款钱包
        AgentWallet remitWallet = agentWalletMapper.queryByAgentId(agentId);

        if (ObjectUtil.isNull(remitWallet)) {
            throw new MyException(CodeMsg.WALLET_IS_NULL);
        }

        BigDecimal remitBalance = remitWallet.getIncomeBalance();

        if (remitBalance.compareTo(orderInfo.getPayMoney()) < 0) {
            throw new MyException(CodeMsg.INSUFFICIENT_BALANCE);
        }

        // 扣除订单支付后的余额
        remitBalance = remitBalance.subtract(orderInfo.getPayMoney());
        remitWallet.setIncomeBalance(remitBalance);


        orderInfo.setOrderState(OrderStateEnum.AWAIT_SEND_OUT.getState());

        return remitWallet.updateById() && orderInfo.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> agentWxPay(Integer orderId, Integer agentId) throws Exception {

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);

        Agent agent = agentMapper.selectById(agentId);

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        } else {
            if (StrUtil.isBlank(agent.getOpenid())) {
                throw new MyException(CodeMsg.OPENID_IS_NULL);
            }
        }

        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        // 商户订单号
        String outTradeNo = orderInfo.getOrderNumber();

        if (StrUtil.isBlank(outTradeNo)) {
            throw new MyException(CodeMsg.ORDER_NUMBER_ERROR);
        }

        String body = "订单描述信息";
        String attach = "{\"type\":" + 1 + "}";
        // 总金额
        // todo
        String totalFee = orderInfo.getPayMoney().multiply(new BigDecimal(100)).intValue() + "";
        String openid = agent.getOpenid();
        Map<String, String> result = wxPayUtil.unifiedorder(body, outTradeNo, attach, totalFee, openid);
        System.err.println(result);
        if ("FAIL".equals(result.get("status"))) {
            log.error("微信支付失败,请联系管理员{}", result);
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String wxPayUnifiedNotify(String notifyXml) {

        synchronized (new Object()) {
            try {
                Map<String, String> notify = WXPayUtil.xmlToMap(notifyXml);
                String orderNumber = notify.get("out_trade_no");
                String appId = notify.get("appid");
                String totalFee = notify.get("total_fee");
                String attach = notify.get("attach");
                // 交易编号
                String transactionId = notify.get("transaction_id");

                log.info("totalFee:\t" + totalFee);

                JSONObject json = JSONUtil.parseObj(attach);
                String type = json.getStr("type");
                // 支付金额
                switch (type) {
                    case "1": {

                        OrderInfo orderInfo = orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>()
                                .eq("order_number", orderNumber)
                                .eq("is_deleted", 0));

                        //判断订单号是否正确
                        if (ObjectUtil.isNull(orderInfo)) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[商户订单不存在]]></return_msg>" + "</xml>";
                        }
                        //判断订单是否是待付款状态(订单状态：-1.已删除0.已关闭1.待确认2.待付款3.待总部处理4.待发货5.待收货6.待评价7.已完成)
                        if (ObjectUtil.isNull(orderInfo.getOrderState()) || orderInfo.getOrderState() != OrderStateEnum.AWAIT_PAYMENT.getState()) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单状态异常,可能已经支付]]></return_msg>" + "</xml>";
                        }

                        log.info("orderInfo.getPayMoney():\t" + orderInfo.getPayMoney());

                        // 数据库查出来的金钱
                        int intValue = orderInfo.getPayMoney().multiply(new BigDecimal("100")).intValue();
                        log.info("intValue:\t" + intValue);
                        log.info("String.valueOf:\t" + String.valueOf(intValue));
                        //判断金额是否一致
                        if (!totalFee.equals(String.valueOf(intValue))) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单金额不一致]]></return_msg>" + "</xml>";
                        }
                        //判断返回码是否是SUCCESS
                        if ("SUCCESS".equals(notify.get("return_code")) && "SUCCESS".equals(notify.get("result_code"))) {
                            //验证签名

                            if (WXPayUtil.isSignatureValid(notify, wxConfig.getKey())) {
                                // 修改订单状态

                                orderInfo.setOrderState(OrderStateEnum.AWAIT_SEND_OUT.getState()).setPayTime(new Date())
                                        .setPayPlatform(2).setPayNumber(transactionId);
                                orderInfo.updateById();

                                if (orderInfo.getIsFirst() == 1) {
                                    List<OrderProduct> orderProductList = orderProductMapper.selectList(new QueryWrapper<OrderProduct>()
                                            .eq("order_number", orderInfo.getOrderNumber()).eq("is_deleted", 0));

                                    if (ObjectUtil.isNotEmpty(orderProductList)) {

                                        BigDecimal totalPrice = new BigDecimal("0");

                                        for (OrderProduct orderProduct : orderProductList) {

                                            Integer productNum = orderProduct.getProductNum();

                                            Integer pid = orderProduct.getPid();
                                            //查询商品邀请奖励
                                            Product product = productMapper.selectById(pid);

                                            BigDecimal reward = product.getInviteReward();
                                            if (ObjectUtil.isNotNull(reward)) {
                                                BigDecimal totalReward = reward.multiply(new BigDecimal(productNum));
                                                // 累计总价钱
                                                totalPrice = totalPrice.add(totalReward);
                                            }
                                        }

                                        Agent agent = agentMapper.selectById(orderInfo.getAgentId());

                                        Integer inviter = agent.getInviter();
                                        // 判断邀请人是否不是总部
                                        if (ObjectUtil.isNotNull(inviter) && inviter > 0) {

                                            //修改钱包余额
                                            AgentWallet remitWallet = agentWalletMapper.queryByAgentId(inviter);
                                            remitWallet.setIncomeBalance(remitWallet.getIncomeBalance().add(totalPrice));
                                            remitWallet.updateById();

                                            // 添加奖励记录
                                            Reward reward = new Reward();
                                            reward.setAgentId(inviter).setRewardSource(agent.getId())
                                                    .setRewardType(4).setSceneType(1).setAmountType(2).setRewardState(3)
                                            .setOrderNumber(orderNumber).setAmount(totalPrice).setExplain("普通一次性奖励");
                                            log.info("reward:\t" + reward);
                                            rewardMapper.insert(reward);
                                            log.info("reward:\t" + reward);
                                        }

                                        agent.setReviewed(1);
                                        agent.updateById();

                                    }
                                }
                                return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
                            }
                        }
                    }
                    break;
                    default:
                        break;
                }

                //判断AppId是否正确
                if (!appId.equals(wxConfig.getAppID())) {
                    return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[app_id不是该商户本身]]></return_msg>" + "</xml>";
                }

            } catch (Exception ex) {
                return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[产生异常.]]></return_msg>" + "</xml>";
            }
        }
        return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[支付失败.]]></return_msg>" + "</xml>";
    }
}
