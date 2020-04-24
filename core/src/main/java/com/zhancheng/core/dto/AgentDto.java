package com.zhancheng.core.dto;

import com.zhancheng.core.entity.AgentCart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentDto
 * @date 2019/10/31 20:07
 */
@Data
public class AgentDto {
    @ApiModelProperty(name = "trueName", value = "真实姓名")
    private String trueName;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;
    @ApiModelProperty(name = "levelId", value = "等级id")
    private Integer levelId;
    @ApiModelProperty(name = "password", value = "登陆密码")
    private String password;
    @ApiModelProperty(name = "inviter", value = "邀请人id")
    private Integer inviter;
    @ApiModelProperty(name = "superior", value = "上级id")
    private Integer superior;
    @ApiModelProperty(name = "code", value = "微信授权code")
    private String code;

    @ApiModelProperty(name = "agentCartList", value = "代理购物车集合")
    private List<AgentCart> agentCartList;
}
