package com.zhancheng.core.vo;

import com.zhancheng.core.entity.StoreVisitor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BianShuHeng
 * @decription
 * @project StoreVisitorVo
 * @date 2019/10/29 16:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StoreVisitorVo extends StoreVisitor {

    @ApiModelProperty(name = "cover", value = "用户头像")
    private String cover;
    @ApiModelProperty(name = "username", value = "用户昵称")
    private String username;
    @ApiModelProperty(name = "storeName", value = "店铺名")
    private String storeName;
    @ApiModelProperty(name = "nickname", value = "店铺名")
    private String nickname;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

}
