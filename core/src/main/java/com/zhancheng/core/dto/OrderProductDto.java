package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderProductDto
 * @date 2019/10/28 14:19
 */
@Data
public class OrderProductDto {

    @ApiModelProperty(name = "pid", value = "商品id", required = true)
    private Integer pid;
    @ApiModelProperty(name = "skuId", value = "商品skuId")
    private Integer skuId;
    @ApiModelProperty(name = "sid", value = "店铺id",required = true)
    private Integer sid;
    @ApiModelProperty(name = "productName", value = "商品名称", required = true)
    private String productName;
    @ApiModelProperty(name = "productNum", value = "商品数量", required = true)
    private Integer productNum = 0;
    @ApiModelProperty(name = "productPrice", value = "商品价格", required = true)
    private BigDecimal productPrice = new BigDecimal("0");
}
