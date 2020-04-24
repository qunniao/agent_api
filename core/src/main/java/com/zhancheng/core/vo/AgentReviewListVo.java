package com.zhancheng.core.vo;

import com.zhancheng.core.entity.AgentRegisterReview;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentReviewListVo
 * @date 2019/10/31 11:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgentReviewListVo extends AgentRegisterReview {

    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;
    @ApiModelProperty(name = "inviterNick", value = "邀请人昵称")
    private String inviterNick;
    @ApiModelProperty(name = "inviterPhone", value = "邀请人手机号")
    private String inviterPhone;
    @ApiModelProperty(name = "inviterLevelNick", value = "邀请人代理等级昵称")
    private String inviterLevelNick;
    @ApiModelProperty(name = "inviterLevel", value = "邀请人代理等级")
    private String inviterLevel;
    @ApiModelProperty(name = "totalPrice", value = "总金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(name = "orderState", value = "订单状态")
    private Integer orderState;
}
