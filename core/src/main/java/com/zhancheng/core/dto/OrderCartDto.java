package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderCartDto
 * @date 2019/10/25 16:09
 */
@Data
public class OrderCartDto {
    @ApiModelProperty(name = "cid", value = "购物车id")
    private Integer cid;
    @ApiModelProperty(name = "num", value = "数量")
    private Integer num;
}
