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

/**
 * 代理加入审核
 * zc_agent_register_review 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_register_review")
public class AgentRegisterReview extends Model<AgentRegisterReview> {

	private static final long serialVersionUID = 1L;


	/**
	 * 代理注册id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 类型：1.自动手动添加代理2.邀请注册
	 */
	private Integer type;

	/**
	 * 邀请人id，0表示总部
	 */
	private Integer inviter;

	/**
	 * 申请信息
	 */
	private String applyInfo;

	/**
	 * 首次进货订单id
	 */
	private Integer orderId;

	/**
	 * 总部收款账户
	 */
	private String payeeAccount;

	/**
	 * 付款金额
	 */
	private BigDecimal paymentAmount;

	/**
	 * 付款凭证（多张图片）
	 */
	private String paymentVoucher;

	/**
	 * 付款人账户
	 */
	private String paymentAccount;

	/**
	 * 驳回原因
	 */
	private String refuseReason;

	/**
	 * 审核状态：0.未审核1.资料审核2.付款审核3.审核通过4.已拒绝
	 */
	private Integer status;

	/**
	 * 付款时间
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
