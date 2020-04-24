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
 * 购物车表
 * zc_agent_cart 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 18:05:12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_cart")
public class AgentCart extends Model<AgentCart> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "cid", value = "购物车id")
	@TableId(value = "cid", type = IdType.AUTO)
	private Integer cid;

	@ApiModelProperty(name = "agentId", value = "代理Id")
	private Integer agentId;

	@ApiModelProperty(name = "pid", value = "商品Id")
	private Integer pid;

	@ApiModelProperty(name = "skuId", value = "skuId")
	private Integer skuId;

	@ApiModelProperty(name = "productName", value = "商品名称")
	private String productName;

	@ApiModelProperty(name = "productNum", value = "商品数量")
	private Integer productNum;

	@ApiModelProperty(name = "productPrice", value = "商品单价")
	private BigDecimal productPrice;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.cid;
}
}
