package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StockDto
 * @date 2019/10/22 11:25
 */
@Data
public class StockDto {

    @ApiModelProperty(name = "pid", value = "商品id")
    private Integer pid;
    @ApiModelProperty(name = "changeType", value = "调整类型")
    private Integer changeType;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    @ApiModelProperty(name = "userType", value = "用户类型")
    private Integer userType;
    @ApiModelProperty(name = "operatorId", value = "后台操作人id")
    private Integer operatorId;
    @ApiModelProperty(name = "num", value = "商品调整数量")
    private Integer num;
    @ApiModelProperty(name = "skuStockDtos", value = "sku库存数组参数：skuId,num")
    private SkuStockDto[] skuStockDtos;
}
