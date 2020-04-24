package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentListVo
 * @date 2019/10/31 10:27
 */
@Data
public class AgentListVo {

    @ApiModelProperty(name = "id", value = "代理人id")
    private Integer id;
    @ApiModelProperty(name = "nickname", value = "代理人昵称")
    private String nickname;
    @ApiModelProperty(name = "reviewed", value = "审核状态：0.未审核/审核中1.审核通过")
    private Integer reviewed;
    @ApiModelProperty(name = "phone", value = "代理人手机号")
    private String phone;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String gmtCreate;
    @ApiModelProperty(name = "agentLevel", value = "代理等级")
    private String agentLevel;
    @ApiModelProperty(name = "levelNick", value = "代理等级昵称")
    private String levelNick;
    @ApiModelProperty(name = "inviterNick", value = "邀请人昵称")
    private String inviterNick;
    @ApiModelProperty(name = "inviterPhone", value = "邀请人手机号")
    private String inviterPhone;
    @ApiModelProperty(name = "superiorNick", value = "上级昵称")
    private String superiorNick;
    @ApiModelProperty(name = "superiorPhone", value = "上级手机号")
    private String superiorPhone;
    @ApiModelProperty(name = "amount", value = "个人业绩总金额")
    private String amount;
    @ApiModelProperty(name = "incomeBalance", value = "钱包余额")
    private BigDecimal incomeBalance;

}
