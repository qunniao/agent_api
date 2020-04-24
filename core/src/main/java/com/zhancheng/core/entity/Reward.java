package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 奖励记录
 * zc_reward 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-13 16:16:08
 */
@Data
@ApiModel(value = "奖励记录")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_reward")
public class Reward extends Model<Reward> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "奖励记录id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "agent_id", value = "代理人id")
	private Integer agentId;

	@ApiModelProperty(name = "reward_type", value = "奖励类型：1.普通订单奖励2.高级市场补贴3.高级一次性奖励4.普通一次性奖励5.教育基金6.个人市场补贴7.基础教育基金8.高级订单奖励9.区域订单奖励")
	private Integer rewardType;

	@ApiModelProperty(name = "reward_source", value = "奖励源")
	private Integer rewardSource;

	@ApiModelProperty(name = "reward_people", value = "奖励人")
	private Integer rewardPeople;

	@ApiModelProperty(name = "amount", value = "奖励金额")
	private BigDecimal amount;

	@ApiModelProperty(name = "order_number", value = "订单号")
	private String orderNumber;

	@ApiModelProperty(name = "amount_type", value = "奖励金额类型：1.固定比例2.固定金额3.按单品设置")
	private Integer amountType;

	@ApiModelProperty(name = "scene_type", value = "奖励场景：1.代理加入2.代理升级")
	private Integer sceneType;

	@ApiModelProperty(name = "explain", value = "说明")
	private String explain;

	@ApiModelProperty(name = "reward_state", value = "状态：1.待结算2.待奖励3.已完成4.已失效")
	private Integer rewardState;

	@ApiModelProperty(name = "gmt_create", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmt_modified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
