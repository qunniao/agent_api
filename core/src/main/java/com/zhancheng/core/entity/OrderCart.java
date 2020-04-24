package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * zc_order_cart 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_order_cart")
public class OrderCart extends Model<OrderCart> {

	private static final long serialVersionUID = 1L;

	/**
	 * 购物车id
	 */
	@TableId(value = "cid", type = IdType.AUTO)
	private Integer cid;

	/**
	 * 店铺id 
	 */
	private Integer sid;

	/**
	 * 用户Id
	 */
	private Integer uid;

	/**
	 * 库存Id
	 */
	private Integer skuId;

	/**
	 * 商品Id
	 */
	private Integer spuId;

	/**
	 * 商品名
	 */
	private String productName;

	/**
	 * 商品加购数量
	 */
	private Integer productNum;

	/**
	 * 商品单价
	 */
	private BigDecimal productPrice;

	/**
	 * 是否删除 0:未删除; 1:删除 
	 */
	@TableLogic
	private Integer isDeleted;

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
		return this.cid;
}
}
