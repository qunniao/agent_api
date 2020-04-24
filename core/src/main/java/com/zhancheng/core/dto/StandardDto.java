package com.zhancheng.core.dto;

import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductGuigeValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription 规格表sku DTO
 * @project StandardDto
 * @date 2019/10/17 16:50
 */
@Data
public class StandardDto {
    @ApiModelProperty(name = "sp1", value = "一级规格", example = "1")
    private String sp1;
    @ApiModelProperty(name = "sp2", value = "二级规格", example = "1")
    private String sp2;
    @ApiModelProperty(name = "productNumber", value = "规格货号", example = "1")
    private String productNumber;
    @ApiModelProperty(name = "price", value = "价格", example = "1")
    private BigDecimal price;
    @ApiModelProperty(name = "weight", value = "重量", example = "1")
    private Integer weight;
    @ApiModelProperty(name = "pic", value = "图片", example = "1")
    private String pic;
}
