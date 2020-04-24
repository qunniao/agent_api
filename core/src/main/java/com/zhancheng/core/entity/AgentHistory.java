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
 * 代理变更记录
 * zc_agent_history 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 10:47:24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_history")
public class AgentHistory extends Model<AgentHistory> {

	private static final long serialVersionUID = 1L;

	/**
	 * 代理记录id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 1.新增代理2.代理注册3.等级变更4.邀请人变更5.进货上级变更6.自动回归7.手动开启8.手动关闭
	 */
	private Integer type;

	/**
	 * 变更前信息
	 */
	private String beforeInfo;

	/**
	 * 变更后信息
	 */
	private String afterInfo;

	/**
	 * 操作人id 0.系统
	 */
	private Integer operatorId;

	/**
	 * 操作人ip
	 */
	private String ip;

	/**
	 * 说明
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
