package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * 管理员
 * zc_admin 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_admin")
@Valid
public class Admin extends Model<Admin> {

	private static final long serialVersionUID = 1L;

	/**
	 * 管理员id
	 */
	@TableId(value = "uid", type = IdType.AUTO)
	private Integer uid;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	@JsonIgnore
	private String pwd;

	/**
	 * 角色id：1.总管理员2.普通管理员3.加盟店
	 */
	private Integer roleId;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 头像
	 */
	private String cover;

	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}$", message = "请输入正确的手机号")
	private String phone;

	/**
	 * 状态 0.关闭1.开启 
	 */
	private Integer status;

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

	@Override
	protected Serializable pkVal() {
		return this.uid;
}
}
