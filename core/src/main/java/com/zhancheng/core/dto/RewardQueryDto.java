package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardQueryDto
 * @date 2019/11/13 16:51
 */
@Data
public class RewardQueryDto {

    @ApiModelProperty(name = "orderNumber", value = "订单号", example = "1")
    private String orderNumber;
    @ApiModelProperty(name = "rewardState", value = "奖励状态", example = "1")
    private String rewardState;
    @ApiModelProperty(name = "agentId", value = "代理id", example = "1")
    private Integer agentId;
}
