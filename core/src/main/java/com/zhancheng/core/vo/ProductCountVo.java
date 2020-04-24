package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductCount
 * @date 2019/10/19 13:36
 */
@Data
public class ProductCountVo {

    @ApiModelProperty(name = "sellNum", value = "出售中的数量")
    private Integer sellNum;
    @ApiModelProperty(name = "soldOutNum", value = "已下架的数量")
    private Integer soldOutNum;
    @ApiModelProperty(name = "sellOutNum", value = "已售完的数量")
    private Integer sellOutNum;
    @ApiModelProperty(name = "warningNum", value = "库存预警数量")
    private Integer warningNum;
}
