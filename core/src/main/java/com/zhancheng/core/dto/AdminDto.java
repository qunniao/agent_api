package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author BianShuHeng
 * @decription
 * @project AdminDto
 * @date 2019/11/11 16:20
 */
@Data
@Valid
public class AdminDto {

    @ApiModelProperty(name = "uid", value = "用户id")
    private Integer uid;

    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    @ApiModelProperty(name = "pwd", value = "密码")
    private String pwd;

    @ApiModelProperty(name = "roleId", value = "角色id：1.总管理员2.普通管理员3.加盟店")
    private Integer roleId;

    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;

    @ApiModelProperty(name = "trueName", value = "真实姓名")
    private String trueName;

    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;

    @ApiModelProperty(name = "phone", value = "手机号")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}$", message = "请输入正确的手机号")
    private String phone;

}
