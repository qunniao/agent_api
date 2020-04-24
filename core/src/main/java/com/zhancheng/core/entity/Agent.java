package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

/**
 * 
 * zc_agent 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent")
public class Agent extends Model<Agent> {

	private static final long serialVersionUID = 1L;

	/**
	 * 代理人id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 姓名
	 */
	private String trueName;

	/**
	 * 手机号 
	 */
	@Pattern(regexp = "^1[3|4|5|7|8][0-9]\\\\d{4,8}$", message = "请输入正确的手机号")
	private String phone;

	/**
	 * 代理等级id
	 */
	private Integer levelId;

	/**
	 * 微信昵称
	 */
	private String nickname;

	/**
	 * 微信id
	 */
	@JsonIgnore
	private String openid;
	/**
	 * 头像
	 */
	private String cover;
	/**
	 * 密码
	 */
	@JsonIgnore
	private String password;
	/**
	 * 邀请人id 0表示总部
	 */
	private Integer inviter;

	/**
	 * 进货上级id 0.表示总部
	 */
	private Integer superior;
	/**
	 * 身份证
	 */
	private String idCard;
	/**
	 * 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String birthday;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 地区
	 */
	private String region;

	/**
	 * 性别：1.男0.女
	 */
	private Integer gender;

	/**
	 * 微信绑定用户id 0.未绑定
	 */
	private Integer userId;

	/**
	 * 加盟店管理员id
	 */
	private Integer adminId;
	/**
	 * 审核状态：0.未审核/审核中1.审核通过
	 */
	private Integer reviewed;

	/**
	 * 状态1.开启0.关闭
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

	/**
	 * 代理等级id
	 */
	@TableField(exist = false)
	private String levelName;

	/**
	 * 邀请人手机号
	 */
	@TableField(exist = false)
	private String inviterPhone;
	/**
	 * 邀请人手机号
	 */
	@TableField(exist = false)
	private BigDecimal incomeBalance;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
