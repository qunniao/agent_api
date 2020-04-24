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
 * 
 * zc_agent_level 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_level")
public class AgentLevel extends Model<AgentLevel> {

	private static final long serialVersionUID = 1L;

	/**
	 * 代理等级id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理等级
	 */
	private String level;

	/**
	 * 等级别名
	 */
	private String nick;

	/**
	 * 是否暗级：0.否1.是
	 */
	private Integer isDarkLevel;

	/**
	 * 进货折扣：按照零售价的等比例折扣计算
	 */
	private BigDecimal stockDiscount;

	/**
	 * 最低进货额度
	 */
	private BigDecimal minStock;

	/**
	 * 授权有效期,单位(月)，0为永久
	 */
	private Integer timeLimit;

	/**
	 * 注册提示语
	 */
	private String registPrompt;

	/**
	 * 加入任务：0.无任务1.有任务
	 */
	private Integer isTask;

	/**
	 * 首次进货类型：1.代理选购2组合套餐
	 */
	private Integer stockType;

	/**
	 * 任务金额
	 */
	private BigDecimal taskAmount;

	/**
	 * 首次进货折扣 1.为默认折扣
	 */
	private BigDecimal firstDiscount;

	/**
	 * 产品组合套餐id
	 */
	private String gids;

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
