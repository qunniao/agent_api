package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.RemitService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.dao.RemitMapper;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.entity.Remit;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.OrderNumUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 打款审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */

@Service
public class RemitServiceImpl extends ServiceImpl<RemitMapper, Remit> implements RemitService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Boolean insert(Remit remit) {

        // 生成打款号
        String remitNumber = OrderNumUtil.generateRemitNumber();

        String orderNumber = remit.getOrderNumber();
        OrderInfo orderInfo = orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>()
                .eq("order_number", orderNumber)
                .eq("is_deleted", 0));
        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        if (orderInfo.getType() == 1){
            orderInfo.setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());
        }else {
            orderInfo.setOrderState(OrderStateEnum.AWAIT_HQ_UNDETERMINED.getState());
        }

        orderInfo.setVoucherState(OrderStateEnum.WAIT_HQ_REVIEW.getState());
        remit.setOrderType(orderInfo.getOrderType());
        remit.setRemitNumber(remitNumber);

        return remit.insert() && orderInfo.updateById();
    }
}