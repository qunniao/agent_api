package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransferListVo {

    @ApiModelProperty(name = "id", value = "转账记录id")
    private Integer id;

    @ApiModelProperty(name = "agentId", value = "代理人id")
    private Integer agentId;

    @ApiModelProperty(name = "agentName", value = "发起人姓名")
    private String agentName;

    @ApiModelProperty(name = "agentPhone", value = "发起人手机")
    private String agentPhone;

    @ApiModelProperty(name = "agentNick", value = "发起人昵称")
    private String agentNick;

    @ApiModelProperty(name = "payeeId", value = "收款人id")
    private Integer payeeId;

    @ApiModelProperty(name = "phone", value = "收款人手机号")
    private String phone;

    @ApiModelProperty(name = "trueName", value = "收款人姓名")
    private String trueName;

    @ApiModelProperty(name = "amount", value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
}