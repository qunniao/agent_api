package com.zhancheng.core.dto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentWalletDTO
 * @date 2019/11/28 15:04
 */
@Data
public class AgentWalletDTO {

    @ApiModelProperty(name = "agentId", value = "代理id")
    private Integer agentId;
    @ApiModelProperty(name = "adminId", value = "操作人id")
    private Integer adminId;
    @ApiModelProperty(name = "changeType", value = "调整类型1：增加，2：减少")
    private Integer changeType;
    @ApiModelProperty(name = "amount", value = "金额")
    private BigDecimal amount;
    @ApiModelProperty(name = "tradeDesc", value = "交易描述信息")
    private String tradeDesc;
}
