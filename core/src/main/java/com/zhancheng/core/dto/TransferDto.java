package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferDto
 * @date 2019/11/8 15:33
 */
@Data
public class TransferDto {

    @ApiModelProperty(name = "agentId", value = "代理id", example = "1")
    private Integer agentId;
    @ApiModelProperty(name = "phone", value = "收款人手机号", example = "1")
    private String phone;
    @ApiModelProperty(name = "trueName", value = "收款人真实姓名", example = "1")
    private String trueName;
    @ApiModelProperty(name = "amount", value = "金额", example = "1")
    private BigDecimal amount;
}
