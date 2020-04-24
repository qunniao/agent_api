package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * 用户
 * zc_user 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:40:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_user")
@Valid
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\\\d{4,8}$", message = "请输入正确的手机号")
    private String phone;

    /**
     * 姓名
     */
    private String trueName;

    /**
     * 第三方
     */
    private String openid;

    /**
     * 会员等级0.普通会员1.钻石会员2.王牌会员
     */
    private Integer memberLevel;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 出生年月日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    /**
     * 0为保密，1为男，2为女
     */
    private Integer gender;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 头像
     */
    private String cover;

    /**
     * 地区
     */
    private String region;

    /**
     * 上级代理
     */
    private Integer superior;
    /**
     * 是否锁定上级：0.否1.是
     */
    private Boolean isLock;

    /**
     * 0是管理员用户,1是普通用户
     */
    private Integer status;

    /**
     * 是否删除 0:未删除; 1:删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtLogin;

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }
}
