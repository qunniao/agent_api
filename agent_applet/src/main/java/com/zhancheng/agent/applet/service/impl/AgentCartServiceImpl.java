package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.AgentCartService;
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
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 购物车表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 18:05:12
 */

@Service
public class AgentCartServiceImpl extends ServiceImpl<AgentCartMapper, AgentCart> implements AgentCartService {

    @Resource
    private AgentCartMapper agentCartMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AgentRegisterReviewMapper agentRegisterReviewMapper;

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderUtil orderUtil;

    @Override
    public List<OrderCartListVo> queryList(Integer agentId) {
        return baseMapper.queryList(agentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer submitCart(AgentOrderDto agentOrderDto) {

        OrderInfo orderInfo = new OrderInfo();
        BeanUtil.copyProperties(agentOrderDto, orderInfo);
        // 根据订单类型生成订单号
        String orderNumber = OrderNumUtil.generateOrderNumber(1);

        // 完善订单信息
        orderInfo.setType(1).setOrderType(1).setOrderNumber(orderNumber)
                .setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());

        List<Integer> cIdList = agentOrderDto.getCidList();
        if (ObjectUtil.isNull(cIdList)) {
            throw new MyException(CodeMsg.ORDER_CART_NOT_EXISTED);
        }

        List<OrderProductDto> orderProductDtoList = new ArrayList<>();

        List<AgentCart> agentCarts = agentCartMapper.selectBatchIds(cIdList);

        for (AgentCart agentCart : agentCarts) {
            OrderProductDto orderProductDto = new OrderProductDto();
            System.err.println(agentCart);
            BeanUtil.copyProperties(agentCart, orderProductDto);
            System.err.println(orderProductDto);
            orderProductDtoList.add(orderProductDto);
        }

        // 删除代理购物车
        agentCartMapper.deleteBatchIds(cIdList);

        // 提交订单并计算总价
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDtoList, orderNumber);

        Integer agentId = agentOrderDto.getAgentId();

        Agent agent = agentMapper.selectById(agentId);

        AgentLevel agentLevel = agentLevelMapper.selectById(agent.getLevelId());

        // 代理首次进货的折扣价格
        BigDecimal temp = totalPrice.multiply(agentLevel.getFirstDiscount());

        // 优惠的金额
        BigDecimal discount = totalPrice.subtract(temp);

        // todo: 折扣后的价格减去运费 实际付款金额
        temp = temp.add(agentOrderDto.getFreight());

        orderInfo.setDiscount(discount);
        orderInfo.setPayMoney(temp);
        orderInfo.setOrderState(OrderStateEnum.AWAIT_PAYMENT.getState());
        orderInfo.setTotalPrice(totalPrice);
        boolean insert = orderInfo.insert();

        // 修改审核状态
        if (ObjectUtil.isNotNull(agentOrderDto.getIsFirst()) && agentOrderDto.getIsFirst() == 1){
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

    @Override
    public Boolean insertOrUpdate(AgentCart agentCart) {

        Integer skuId = agentCart.getSkuId();
        Integer pid = agentCart.getPid();

        // 购买的产品数量
        Integer productNum = agentCart.getProductNum();

        if (ObjectUtil.isNotNull(agentCart.getSkuId())) {
            ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(agentCart.getSkuId());
            if (ObjectUtil.isNull(productGuigeSku)) {
                throw new MyException(CodeMsg.PRODUCT_SKU_NOT_EXISTED);
            }

            Integer availableStock = productGuigeSku.getAvailableStock();

            AgentCart agentCartInfo = baseMapper.selectOne(new QueryWrapper<AgentCart>()
                    .eq("agent_id",agentCart.getAgentId())
                    .eq("sku_id", skuId));

            // 如果购物车存在，就增加数量
            if (ObjectUtil.isNotNull(agentCartInfo)){
                agentCartInfo.setProductNum(agentCartInfo.getProductNum() + productNum);
                return agentCartInfo.updateById();
            }

            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (productNum < 1 || availableStock < productNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }

        } else {
            Product product = productMapper.selectById(agentCart.getPid());
            if (ObjectUtil.isNull(product)) {
                throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
            }

            Integer availableStock = product.getAvailableStock();

            AgentCart agentCartInfo = baseMapper.selectOne(new QueryWrapper<AgentCart>()
                    .eq("agent_id",agentCart.getAgentId()).eq("pid", pid));

            // 如果购物车存在，就增加数量
            if (ObjectUtil.isNotNull(agentCartInfo)){
                agentCartInfo.setProductNum(agentCartInfo.getProductNum() + productNum);
                return agentCartInfo.updateById();
            }

            //购物车每个sku的商品数量最少是1 ||加购数量小于库存
            if (productNum < 1 || availableStock < productNum) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }
        }

        return agentCart.insertOrUpdate();
    }

    @Override
    public Boolean changeProductNum(OrderCartDto orderCartDto) {

        Integer productNum = orderCartDto.getNum();

        int num;

        // 购物车id
        Integer cid = orderCartDto.getCid();

        AgentCart agentCart = baseMapper.selectById(cid);

        if (ObjectUtil.isNull(agentCart)) {
            throw new MyException(CodeMsg.ORDER_CART_NOT_EXISTED);
        }
        ProductGuigeSku productGuigeSku;
        Product product;
        if (ObjectUtil.isNotNull(agentCart.getSkuId())) {
            productGuigeSku = productGuigeSkuMapper.selectById(agentCart.getSkuId());
            if (ObjectUtil.isNull(productGuigeSku)) {
                throw new MyException(CodeMsg.PRODUCT_SKU_NOT_EXISTED);
            }
            num = productGuigeSku.getAvailableStock();
        } else {
            product = productMapper.selectById(agentCart.getPid());
            if (ObjectUtil.isNull(product)) {
                throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
            }
            num = product.getAvailableStock();
        }

        //购物车每个sku的商品数量最少是1 ||加购数量小于库存
        if (productNum < 1 || num < productNum) {
            throw new MyException(CodeMsg.GOODS_NUM_ERROR);
        }

        agentCart.setProductNum(productNum);

        return agentCart.updateById();
    }

    @Override
    public List<OrderCartListVo> queryInfo(List<Integer> cIds) {
        return baseMapper.queryInfo(cIds);
    }
}