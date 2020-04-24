package com.zhancheng.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dao.ProductGuigeSkuMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dto.OrderProductDto;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductGuigeSku;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderUtil
 * @date 2019/11/5 11:55
 */
@Component
public class OrderUtil {

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductMapper productMapper;

    public BigDecimal addOrderProduct(List<OrderProductDto> orderProductDtoList, String orderNumber) {

        BigDecimal totalPrice = new BigDecimal("0");
        // 判断订单中是否包含产品
        if(ObjectUtil.isEmpty(orderProductDtoList)){
            throw new MyException(CodeMsg.ORDER_PRODUCT_IS_NULL);
        }

        for (OrderProductDto orderProductDto : orderProductDtoList) {

            if (ObjectUtil.isNotNull(orderProductDto)){
                OrderProduct orderProduct = new OrderProduct();
                BeanUtil.copyProperties(orderProductDto, orderProduct);
                orderProduct.setOrderNumber(orderNumber);
                orderProduct.insert();
                Product product = productMapper.selectById(orderProductDto.getPid());
                ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectById(orderProductDto.getSkuId());

                if (ObjectUtil.isNull(product)){
                    throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
                }

                BigDecimal productPrice = orderProductDto.getProductPrice();
                Integer productNum = orderProductDto.getProductNum();

                if(ObjectUtil.isNotNull(productGuigeSku)){
                    Integer availableStock = productGuigeSku.getAvailableStock();
                    if (productNum < 1 || productNum > availableStock){
                        throw new MyException(CodeMsg.GOODS_NUM_ERROR);
                    }

                    // 调整 sku锁定库存和 可用库存
                    productGuigeSku.setLockStock(productGuigeSku.getLockStock() + productNum);
                    productGuigeSku.setAvailableStock(availableStock - productNum);
                    productGuigeSku.updateById();
                }

                // 调整 商品锁定库存和 可用库存
                product.setLockStock(product.getLockStock() + productNum);
                product.setAvailableStock(product.getAvailableStock() - productNum);
                product.updateById();

                //单价乘数量。获得单个商品总价
                BigDecimal multiply = productPrice.multiply(new BigDecimal(productNum));
                //累积总价钱
                totalPrice = totalPrice.add(multiply);
            }
        }

        return totalPrice;
    }
}
