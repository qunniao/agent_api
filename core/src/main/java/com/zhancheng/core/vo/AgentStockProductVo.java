package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentStockProductVo
 * @date 2019/11/2 14:01
 */
@Data
public class AgentStockProductVo {

    @ApiModelProperty(name = "id", value = "代理等级id")
    private Integer id;
    @ApiModelProperty(name = "agentId", value = "代理id")
    private Integer agentId;
    @ApiModelProperty(name = "stock", value = "库存")
    private Integer stock;
    @ApiModelProperty(name = "productName", value = "商品名称")
    private Integer productName;
    @ApiModelProperty(name = "cover", value = "头像")
    private Integer cover;
    @ApiModelProperty(name = "sp1", value = "一级规格")
    private Integer sp1;
    @ApiModelProperty(name = "sp2", value = "二级规格")
    private Integer sp2;
}
