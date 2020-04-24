package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 代理库存
 * zc_agent_stock 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 16:02:47
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_stock")
public class AgentStock extends Model<AgentStock> {

	private static final long serialVersionUID = 1L;


	/**
	 * 代理库存id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 库存类型1.实体2.云库存
	 */
	private Integer type;

	/**
	 * 商品种类数量
	 */
	private Integer ptNumber;

	/**
	 * 总库存
	 */
	private String stock;

	/**
	 * 商品进货总价
	 */
	private BigDecimal totalPrice;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
