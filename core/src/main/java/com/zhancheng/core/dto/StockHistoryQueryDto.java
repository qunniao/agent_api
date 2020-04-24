package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StockHistoryQueryDto
 * @date 2019/10/23 17:08
 */
@Data
public class StockHistoryQueryDto {

    @ApiModelProperty(name = "search", value = "搜索")
    private String search;
    @ApiModelProperty(name = "changeType", value = "变更项")
    private Integer changeType;
    @ApiModelProperty(name = "startTime", value = "开始时间")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "结束时间")
    private String endTime;
    @ApiModelProperty(name = "orderType", value = "订单类型")
    private Integer orderType;
    @ApiModelProperty(name = "userType", value = "用户类型")
    private Integer userType;
    @ApiModelProperty(name = "agentLevel", value = "代理等级")
    private Integer agentLevel;
    @ApiModelProperty(name = "agentInfo", value = "代理个人信息")
    private String agentInfo;
    @ApiModelProperty(name = "stockType", value = "库存类型：总部或者代理")
    private Integer stockType;
}
