package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentOrderDto
 * @date 2019/11/5 11:05
 */
@Data
public class AgentOrderDto {
    @ApiModelProperty(name = "agentId", value = "代理id")
    private Integer agentId;
    @ApiModelProperty(name = "uid", value = "用户id")
    private Integer uid;
    @ApiModelProperty(name = "storeId", value = "店铺id")
    private Integer storeId;
    @ApiModelProperty(name = "storePersonId", value = "店铺所属人id")
    private Integer storePersonId;
    @ApiModelProperty(name = "orderType", value = "订单类型")
    private Integer orderType;
    @ApiModelProperty(name = "contactName", value = "联系人")
    private String contactName;
    @ApiModelProperty(name = "contactPhone", value = "联系手机号")
    private String contactPhone;
    @ApiModelProperty(name = "contactAddress", value = "联系地址")
    private String contactAddress;
    @ApiModelProperty(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流")
    private Integer sendWay;
    @ApiModelProperty(name = "freight", value = "运费")
    private BigDecimal freight;
    @ApiModelProperty(name = "discount", value = "优惠金额")
    private BigDecimal discount;
    @ApiModelProperty(name = "isCloud", value = "是否云订单：1.是0.否")
    private Integer isCloud;
    @ApiModelProperty(name = "isOpen", value = "是否拆单：1.是0.否")
    private Integer isOpen;
    @ApiModelProperty(name = "isFirst", value = "是否首进1.是0.否")
    private Integer isFirst;
    @ApiModelProperty(name = "isForward", value = "是否转代发1.是0.否")
    private Integer isForward;
    @ApiModelProperty(name = "isCloudForward", value = "是否云代发1.是0.否")
    private Integer isCloudForward;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    @ApiModelProperty(name = "cidList", value = "购物车id集合")
    private List<Integer> cidList;
}
