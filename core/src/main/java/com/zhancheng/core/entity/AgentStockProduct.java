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
 * 代理库存商品
 * zc_agent_stock_product 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-02 11:47:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_agent_stock_product")
public class AgentStockProduct extends Model<AgentStockProduct> {

	private static final long serialVersionUID = 1L;


	/**
	 * 库存商品id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 店铺id
	 */
	private Integer storeId;

	/**
	 * 库存类型1.实体2.云库存
	 */
	private Integer type;

	/**
	 * 商品id
	 */
	private Integer productId;

	/**
	 * 商品规格id
	 */
	private Integer skuId;

	/**
	 * 商品进货价
	 */
	private BigDecimal importPrice;

	/**
	 * 库存
	 */
	private Integer stock;

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
