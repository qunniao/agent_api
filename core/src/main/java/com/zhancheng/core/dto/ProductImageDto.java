package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductImageDto
 * @date 2019/10/17 17:32
 */
@Data
public class ProductImageDto {

    @ApiModelProperty(name = "url", value = "商品图片地址", example = "1")
    private String url;
    @ApiModelProperty(name = "sort", value = "排序：排序数字高的商品排列在前", example = "1")
    private Integer sort;
}
