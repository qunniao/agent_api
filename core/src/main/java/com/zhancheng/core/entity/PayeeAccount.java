package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 总部账户
 * zc_payee_account 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 18:02:48
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_payee_account")
public class PayeeAccount extends Model<PayeeAccount> {

	private static final long serialVersionUID = 1L;

	/**
	 * 总部账户
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理人id 0.总部
	 */
	private Integer agentId;
	/**
	 * 账户类别1.银行卡2.支付宝3.微信
	 */
	private Integer type;

	/**
	 * 姓名
	 */
	private String trueName;

	/**
	 * 账户
	 */
	private String account;

	/**
	 * 账户
	 */
	private String depositBank;

	/**
	 * 收款二维码
	 */
	private String qrCode;

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
		return this.id;
}
}
