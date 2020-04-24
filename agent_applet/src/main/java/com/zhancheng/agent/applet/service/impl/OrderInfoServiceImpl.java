package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.OrderInfoService;
import com.zhancheng.agent.applet.service.OrderProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.OrderInfoDto;
import com.zhancheng.core.dto.OrderProductDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.OrderNumUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.OrderProductVo;
import com.zhancheng.core.vo.RetailOrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderUtil orderUtil;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private AgentRegisterReviewMapper agentRegisterReviewMapper;

    @Override
    public IPage<OrderInfo> selectPage(PageDto<OrderInfo> pageDto, OrderQueryDto orderQueryDto) {
        IPage<OrderInfo> orderInfoIPage = baseMapper.queryPage(pageDto.getPage(), orderQueryDto);
        List<OrderInfo> records = orderInfoIPage.getRecords();
        if (ObjectUtil.isNotNull(records)){
            for (OrderInfo orderInfo : records) {
                List<OrderProductVo> orderProductList = orderProductMapper.queryInfo(orderInfo.getOrderNumber());
                orderInfo.setOrderProductList(orderProductList);
            }
        }
        return orderInfoIPage;
    }

    @Override
    public RetailOrderInfoVo info(Integer oid) {

        RetailOrderInfoVo retailOrderInfoVo = baseMapper.queryInfo(oid);

        if (ObjectUtil.isNull(retailOrderInfoVo)){
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        List<OrderProductVo> orderProductList = orderProductMapper.queryInfo(retailOrderInfoVo.getOrderNumber());
        retailOrderInfoVo.setOrderProductList(orderProductList);
        return retailOrderInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(OrderInfoDto orderInfoDto) {

        OrderInfo orderInfo = new OrderInfo();

        BeanUtil.copyProperties(orderInfoDto, orderInfo);

        if (ObjectUtil.isNotNull(orderInfoDto.getInviter()) && orderInfoDto.getInviter() > 0) {
            orderInfo.setType(2);
        }else {
            orderInfo.setType(1);
        }

        // 根据订单类型生成订单号
        String orderNumber = OrderNumUtil.generateOrderNumber(orderInfoDto.getOrderType());
        List<OrderProductDto> orderProductDtoList = orderInfoDto.getOrderProductDtoList();

        // 添加产品订单并计算总价格
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDtoList, orderNumber);

        // todo: 总价减去优惠
        BigDecimal temp = totalPrice.subtract(orderInfoDto.getDiscount());

        // 加上运费
        temp = temp.add(orderInfoDto.getFreight());

        orderInfo.setDiscount(orderInfoDto.getDiscount());
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());
        orderInfo.setPayMoney(temp);
        orderInfo.setOrderNumber(orderNumber);
        orderInfo.insert();

        // 修改审核状态
        if (orderInfoDto.getIsFirst() == 1){
            // 修改审核状态
            AgentRegisterReview agentRegisterReview = agentRegisterReviewMapper.selectOne(new QueryWrapper<AgentRegisterReview>()
                    .eq("agent_id", orderInfoDto.getAgentId()));

            // 判断是否存在审核状态
            if (ObjectUtil.isNull(agentRegisterReview)){
                throw new MyException(CodeMsg.AGENT_REVIEW_IS_NULL);
            }

            agentRegisterReview.setOrderId(orderInfo.getOid());
            agentRegisterReview.updateById();
        }

        return orderInfo.getOid();
    }

    @Override
    public Boolean update(OrderInfo orderInfo) {
        return baseMapper.updateById(orderInfo) > 0;
    }

    @Override
    public Boolean delete(Integer oid) {

        OrderInfo orderInfo = baseMapper.selectById(oid);

        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        orderInfo.setOrderState(OrderStateEnum.DELETE.getState());

        return orderInfo.updateById();
    }


}