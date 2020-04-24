package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductParamDto
 * @date 2019/10/17 17:44
 */
@Data
public class ProductParamDto {

    @ApiModelProperty(name = "name", value = "商品属性名", example = "1")
    private String name;
    @ApiModelProperty(name = "value", value = "商品属性值", example = "1")
    private String value;
    @ApiModelProperty(name = "sort", value = "排序：排序数字高的商品排列在前", example = "1")
    private Integer sort;
}
