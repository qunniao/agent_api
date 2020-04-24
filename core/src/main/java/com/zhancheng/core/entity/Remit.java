package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 打款审核
 * zc_remit 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_remit")
@Valid
public class Remit extends Model<Remit> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 打款号
	 */
	private String remitNumber;

	/**
	 * 付款人id
	 */
	private String payerId;

	/**
	 * 收款账户id
	 */
	private String accountId;

	/**
	 * 订单类型1.采购2.零售3.提货4.内购5.换货
	 */
	private Integer orderType;

	/**
	 * 订单号
	 */
	private String orderNumber;

	/**
	 * 打款金额
	 */
	private BigDecimal amount;

	/**
	 * 打款人姓名
	 */
	private String remitterName;

	/**
	 * 打款人电话
	 */
	private String remitterPhone;

	/**
	 * 打款账户
	 */
	private String remitAccount;

	/**
	 * 收款账户
	 */
	private String payeeAccount;

	/**
	 * 审核人：1.总部2.代理3.自动审核
	 */
	private Integer auditorType;

	/**
	 * 付款凭证（多张图片）
	 */
	private String paymentVoucher;

	/**
	 * 审核状态：0.待审核1.审核通过2.已拒绝
	 */
	private Integer reviewState;

	/**
	 * 驳回原因
	 */
	private String refuseReason;

	/**
	 * 备注信息
	 */
	private String remark;

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
