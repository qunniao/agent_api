package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderInfoService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.BackOrderQueryDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.vo.OrderBackListVo;
import com.zhancheng.core.vo.OrderInfoVo;
import com.zhancheng.core.vo.OrderProductBackVo;
import com.zhancheng.core.vo.OrderProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private AgentMapper agentMapper;

    @Override
    public IPage<OrderBackListVo> selectPage(PageDto<OrderInfo> pageDto, BackOrderQueryDto backOrderQueryDto) {

        IPage<OrderBackListVo> orderBackListPage = baseMapper.queryBackPage(pageDto.getPage(), backOrderQueryDto);
        List<OrderBackListVo> records = orderBackListPage.getRecords();

        if (ObjectUtil.isNotNull(records)) {
            for (OrderBackListVo orderBackListVo : records) {
                String orderNumber = orderBackListVo.getOrderNumber();

                List<OrderProductBackVo> orderProductBackList = orderProductMapper.queryBackInfo(orderNumber);
                orderBackListVo.setProductNum(orderProductBackList.size());
                orderBackListVo.setOrderProductBackList(orderProductBackList);
            }
        }
        return orderBackListPage;
    }

    @Override
    public OrderInfoVo info(Integer oid) {

        // todo:查询订单详情
        // 查询订单详情
        OrderInfoVo orderInfoVo = baseMapper.queryDetails(oid, null);

        if (ObjectUtil.isNull(orderInfoVo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        // 查询用户是否是代理
        String agentLevel = agentMapper.queryAgentLevel(orderInfoVo.getAgentId());
        orderInfoVo.setUserAgentLevel(agentLevel);

        // 查询sku数据
        List<OrderProductBackVo> orderProductBackList = orderProductMapper.queryBackInfo(orderInfoVo.getOrderNumber());
        orderInfoVo.setOrderProductBackList(orderProductBackList);

        return orderInfoVo;
    }

    @Override
    public OrderInfoVo details(String orderNumber) {
        // todo:查询订单详情
        // 查询订单详情
        OrderInfoVo orderInfoVo = baseMapper.queryDetails(null, orderNumber);

        if (ObjectUtil.isNull(orderInfoVo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        // 查询用户是否是代理
        String agentLevel = agentMapper.queryAgentLevel(orderInfoVo.getAgentId());
        orderInfoVo.setUserAgentLevel(agentLevel);

        // 查询sku数据
        List<OrderProductBackVo> orderProductBackList = orderProductMapper.queryBackInfo(orderInfoVo.getOrderNumber());
        orderInfoVo.setOrderProductBackList(orderProductBackList);

        return orderInfoVo;

    }

    @Override
    public Boolean shipments(Integer oid) {

        OrderInfo orderInfo = baseMapper.selectById(oid);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        orderInfo.setOrderState(OrderStateEnum.AWAIT_RECEIVING.getState());

        return orderInfo.updateById();
    }

}