package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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

/**
 * 代理人钱包
 * zc_agent_wallet 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 13:53:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_wallet")
public class AgentWallet extends Model<AgentWallet> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "钱包id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "id", value = "钱包id")
	private Integer agentId;

	@ApiModelProperty(name = "id", value = "支付密码")
	@JsonIgnore
	private String password;

	@ApiModelProperty(name = "incomeBalance", value = "收入余额")
	private BigDecimal incomeBalance;

	@ApiModelProperty(name = "withdrawalBalance", value = "提现中收入余额")
	private BigDecimal withdrawalBalance;

	@ApiModelProperty(name = "settlementBalance", value = "待结算收入余额")
	private BigDecimal settlementBalance;

	@ApiModelProperty(name = "withdrawalObject", value = "可提现账户对象")
	private String withdrawalObject;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
