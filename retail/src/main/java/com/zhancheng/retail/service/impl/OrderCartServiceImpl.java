package com.zhancheng.retail.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;
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
import com.zhancheng.retail.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderCartMapper orderCartMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private OrderUtil orderUtil;

    @Override
    public IPage<OrderCartListVo> selectPage(PageDto<OrderCart> pageDto, Integer uid) {
        return baseMapper.queryPage(pageDto.getPage(), uid);
    }

    @Override
    public List<OrderCartListVo> info(List<Integer> cIds) {

        return baseMapper.queryInfo(cIds);
    }

    @Override
    public Boolean insertOrUpdate(OrderCart orderCart) {

        Integer skuId = orderCart.getSkuId();
        Integer spuId = orderCart.getSpuId();

        // 购买的产品数量
        Integer productNum = orderCart.getProductNum();

        if (ObjectUtil.isNotNull(skuId)) {

            ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(orderCart.getSkuId());
            if (ObjectUtil.isNull(productGuigeSku)) {
                throw new MyException(CodeMsg.PRODUCT_SKU_NOT_EXISTED);
            }

            Integer availableStock = productGuigeSku.getAvailableStock();

            OrderCart orderCartInfo = baseMapper.selectOne(new QueryWrapper<OrderCart>()
                    .eq("uid",orderCart.getUid())
                    .eq("sku_id", skuId));

            // 如果购物车存在，就增加数量
            if (ObjectUtil.isNotNull(orderCartInfo)){
                orderCartInfo.setProductNum(orderCartInfo.getProductNum() + productNum);
                return orderCartInfo.updateById();
            }

            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (productNum < 1 || availableStock < productNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }

        } else {
            Product product = productMapper.selectById(orderCart.getSpuId());
            if (ObjectUtil.isNull(product)) {
                throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
            }
            Integer availableStock = product.getAvailableStock();

            OrderCart orderCartInfo = baseMapper.selectOne(new QueryWrapper<OrderCart>()
                    .eq("uid",orderCart.getUid())
                    .eq("spu_id", spuId));

            // 如果购物车存在，就增加数量
            if (ObjectUtil.isNotNull(orderCartInfo)){
                orderCartInfo.setProductNum(orderCartInfo.getProductNum() + productNum);
                return orderCartInfo.updateById();
            }

            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (productNum < 1 || availableStock < productNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }
        }

        return orderCart.insertOrUpdate();
    }

    @Override
    public Boolean changeProductNum(OrderCartDto orderCartDto) {

        Integer productNum = orderCartDto.getNum();

        int num;

        // 购物车id
        Integer cid = orderCartDto.getCid();

        OrderCart orderCart = baseMapper.selectById(cid);

        if(ObjectUtil.isNull(orderCart)){
            throw new MyException(CodeMsg.ORDER_CART_NOT_EXISTED);
        }
        ProductGuigeSku productGuigeSku;
        Product product;
        if (ObjectUtil.isNotNull(orderCart.getSkuId())){
            productGuigeSku = productGuigeSkuMapper.selectById(orderCart.getSkuId());
            if (ObjectUtil.isNull(productGuigeSku)) {
                throw new MyException(CodeMsg.PRODUCT_SKU_NOT_EXISTED);
            }
            num = productGuigeSku.getAvailableStock();
        }else {
            product = productMapper.selectById(orderCart.getSpuId());
            if (ObjectUtil.isNull(product)) {
                throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
            }
            num = product.getAvailableStock();
        }

        //购物车每个sku的商品数量最少是1 ||加购数量小于库存
        if (productNum < 1 || num < productNum) {
            throw new MyException(CodeMsg.GOODS_NUM_ERROR);
        }

        orderCart.setProductNum(productNum);

        return orderCart.updateById();
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
            stockDiscount  = agentLevel.getStockDiscount();
        }

        // 提交订单并计算总价
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDtoList, orderNumber);

        // 代理首次进货的折扣价格
        BigDecimal temp = totalPrice.multiply(stockDiscount);
        // 优惠的金额
        BigDecimal discount = totalPrice.subtract(temp);

        // todo: 折扣后的价格减去运费
        totalPrice = temp.subtract(agentOrderDto.getFreight());

        orderInfo.setDiscount(discount);
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.insert();

        return orderInfo.getOid();
    }

}