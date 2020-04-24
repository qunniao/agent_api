package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.OrderCartService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.dto.OrderProductDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.OrderNumUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.OrderCartListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class OrderCartServiceImpl extends ServiceImpl<OrderCartMapper, OrderCart> implements OrderCartService {

    @Autowired
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderCartMapper orderCartMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AgentRegisterReviewMapper agentRegisterReviewMapper;

    @Autowired
    private OrderUtil orderUtil;

    @Override
    public IPage<OrderCartListVo> selectPage(PageDto<OrderCart> pageDto, Integer uid) {
        return baseMapper.queryPage(pageDto.getPage(), uid);
    }

    @Override
    public OrderCart info(Integer cid) {

        return baseMapper.selectById(cid);
    }


    @Override
    public Boolean delete(List<Integer> cids) {
        return baseMapper.deleteBatchIds(cids) > 0;
    }

    @Override
    public Integer submitCart(AgentOrderDto agentOrderDto) {

        OrderInfo orderInfo = new OrderInfo();
        BeanUtil.copyProperties(agentOrderDto, orderInfo);
        // 根据订单类型生成订单号
        String orderNumber = OrderNumUtil.generateOrderNumber(1);

        // 完善订单信息
        orderInfo.setType(1).setOrderType(1).setOrderNumber(orderNumber)
                .setIsCloudForward(1).setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());

        List<Integer> cIdList = agentOrderDto.getCidList();
        if (ObjectUtil.isNull(cIdList)) {
            throw new MyException(CodeMsg.ORDER_CART_NOT_EXISTED);
        }

        List<OrderProductDto> orderProductDtoList = new ArrayList<>();

        List<OrderCart> orderCarts = orderCartMapper.selectBatchIds(cIdList);

        for (OrderCart orderCart : orderCarts) {

            OrderProductDto orderProductDto = new OrderProductDto();
            System.err.println(orderCart);
            BeanUtil.copyProperties(orderCart, orderProductDto);
            System.err.println(orderProductDto);
            orderProductDtoList.add(orderProductDto);
        }

        // 删除用户购物车
        orderCartMapper.deleteBatchIds(cIdList);
        // 判断是否是采购订单。并查询等级的折扣
        BigDecimal stockDiscount = new BigDecimal("1");
        if (ObjectUtil.isNotNull(agentOrderDto.getAgentId()) && agentOrderDto.getOrderType() == 1){
            Integer agentId = agentOrderDto.getAgentId();
            Agent agent = agentMapper.selectById(agentId);
            AgentLevel agentLevel = agentLevelMapper.selectById(agent.getLevelId());
            if (ObjectUtil.isNotNull(agentLevel) && agentLevel.getId() != 9){
                stockDiscount  = agentLevel.getStockDiscount();
            }
        }

        // 提交订单并计算总价
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDtoList, orderNumber);

        // 代理首次进货的折扣价格
        BigDecimal temp = totalPrice.multiply(stockDiscount);
        // 优惠的金额
        BigDecimal discount = totalPrice.subtract(temp);

        // todo: 折扣后的价格减去运费
        temp = temp.add(agentOrderDto.getFreight());

        orderInfo.setDiscount(discount);
        orderInfo.setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());
        orderInfo.setPayMoney(temp);
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.insert();

        // 修改审核状态
        if (agentOrderDto.getIsFirst() == 1){

            AgentRegisterReview agentRegisterReview = agentRegisterReviewMapper.selectOne(new QueryWrapper<AgentRegisterReview>()
                    .eq("agent_id", agentOrderDto.getAgentId()));

            if (ObjectUtil.isNull(agentRegisterReview)){
                throw new MyException(CodeMsg.AGENT_REVIEW_IS_NULL);
            }
            agentRegisterReview.setOrderId(orderInfo.getOid());
            agentRegisterReview.updateById();
        }

        return orderInfo.getOid();
    }

}