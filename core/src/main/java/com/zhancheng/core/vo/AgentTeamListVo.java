package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentTeamListVo
 * @date 2019/11/1 17:20
 */
@Data
public class AgentTeamListVo {

    @ApiModelProperty(name = "agentId", value = "代理人id")
    private Integer agentId;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;
    @ApiModelProperty(name = "nickName", value = "昵称")
    private String nickName;
    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;
    @ApiModelProperty(name = "nick", value = "代理等级昵称")
    private String nick;
}
