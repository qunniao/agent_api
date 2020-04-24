package com.zhancheng.retail.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dao.ProductGuigeSkuMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dto.OrderInfoDto;
import com.zhancheng.core.dto.OrderProductDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.OrderNumUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.OrderListVo;
import com.zhancheng.core.vo.OrderProductVo;
import com.zhancheng.core.vo.RetailOrderInfoVo;
import com.zhancheng.retail.service.OrderInfoService;
import com.zhancheng.retail.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderUtil orderUtil;

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
        List<OrderProductDto> orderProductDtoList = orderInfoDto.getOrderProductDtoList();

        // 根据订单类型生成订单号
        String orderNumber = OrderNumUtil.generateOrderNumber(orderInfoDto.getOrderType());

        // 提交订单并计算总价
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDtoList, orderNumber);

        // todo: 总价减去运费
        totalPrice = totalPrice.subtract(orderInfoDto.getFreight());

        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setOrderNumber(orderNumber);
        orderInfo.insert();
        return orderInfo.getOid();
    }

    @Override
    public Boolean update(OrderInfo orderInfo) {
        return baseMapper.updateById(orderInfo) > 0;
    }

    @Override
    public Boolean delete(List<Integer> oids) {
        return baseMapper.deleteBatchIds(oids) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean shipments(Integer oid) {

        // 查询订单信息
        OrderInfo orderInfo = baseMapper.selectOne(new QueryWrapper<OrderInfo>()
                        .eq("oid", oid).eq("is_deleted", 0)
                        .eq("order_state", 4));
        if (ObjectUtil.isNull(orderInfo)) {
            throw new MyException(CodeMsg.ORDER_ERROR);
        }
        // 修改订单状态为待收货 5
        orderInfo.setOrderState(OrderStateEnum.AWAIT_RECEIVING.getState());

        List<OrderProduct> orderProductList = orderProductMapper.selectList(new QueryWrapper<OrderProduct>()
                        .eq("order_number",orderInfo.getOrderNumber()).eq("is_deleted", 0));

        if (ObjectUtil.isEmpty(orderProductList)) {
            throw new MyException(CodeMsg.ORDER_PRODUCT_IS_NULL);
        }

        // 是否是云订单
        Integer isCloud = orderInfo.getIsCloud();

        // 不是云订单
        if (ObjectUtil.isNull(isCloud) || isCloud == 0){
            for (OrderProduct orderProduct : orderProductList) {

                Integer productNum = orderProduct.getProductNum();
                Integer skuId = orderProduct.getSkuId();
                Integer pid = orderProduct.getPid();

                if (isCloud == 1){

                }

                // 减少 sku库存
                if (ObjectUtil.isNotNull(skuId)) {
                    ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(skuId);
                    productGuigeSku.setTotalStock(productGuigeSku.getTotalStock() - productNum);
                    productGuigeSku.setAvailableStock(productGuigeSku.getAvailableStock() - productNum);
                    productGuigeSku.setLockStock(productGuigeSku.getLockStock() - productNum);
                    productGuigeSku.updateById();
                }

                Product product = productMapper.selectById(pid);
                product.setTotalStock(product.getTotalStock() - productNum);
                product.setAvailableStock(product.getAvailableStock() - productNum);
                product.setLockStock(product.getLockStock() - productNum);
                product.updateById();

            }
            // 云订单
        }else {
            // 设置订单状态为已完成
            orderInfo.setOrderState(OrderStateEnum.COMPLETED.getState());

            // 添加代理库存商品
            for (OrderProduct orderProduct : orderProductList) {

                Integer productNum = orderProduct.getProductNum();
                Integer skuId = orderProduct.getSkuId();
                Integer pid = orderProduct.getPid();
                AgentStockProduct agentStockProduct = new AgentStockProduct();
                agentStockProduct.setAgentId(orderInfo.getAgentId());
                agentStockProduct.setStoreId(orderProduct.getSid());
                agentStockProduct.setType(2);
                agentStockProduct.setProductId(orderProduct.getPid());
                agentStockProduct.setSkuId(orderProduct.getSkuId());
                agentStockProduct.setImportPrice(orderProduct.getProductPrice());
                agentStockProduct.setStoreId(orderProduct.getProductNum());
            }
        }

        return null;
    }

}