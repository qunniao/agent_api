package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AgentInfoVo {

    @ApiModelProperty(name = "id", value = "代理人id")
    private Integer id;
    @ApiModelProperty(name = "trueName", value = "代理人姓名")
    private String trueName;
    @ApiModelProperty(name = "nickname", value = "代理人昵称")
    private String nickname;
    @ApiModelProperty(name = "phone", value = "代理人手机号")
    private String phone;
    @ApiModelProperty(name = "reviewed", value = "审核状态：0.未审核/审核中1.审核通过")
    private Integer reviewed;
    @ApiModelProperty(name = "levelId", value = "代理人等级id")
    private Integer levelId;
    @ApiModelProperty(name = "cover", value = "代理人头像")
    private String cover;
    @ApiModelProperty(name = "region", value = "代理人地区")
    private String region;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
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
    @ApiModelProperty(name = "storeName", value = "店铺名称")
    private String storeName;
    @ApiModelProperty(name = "storeCover", value = "店铺头像")
    private String storeCover;
    @ApiModelProperty(name = "adminName", value = "管理员姓名")
    private String adminName;
    @ApiModelProperty(name = "adminPhone", value = "管理员手机号")
    private String adminPhone;
    @ApiModelProperty(name = "totalPrice", value = "进货总金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(name = "monthPrice", value = "最近30天进货金额")
    private BigDecimal monthPrice;
    @ApiModelProperty(name = "incomeBalance", value = "钱包余额")
    private BigDecimal incomeBalance;
}