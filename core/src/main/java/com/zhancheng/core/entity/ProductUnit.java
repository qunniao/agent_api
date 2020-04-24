package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品单位
 * zc_product_unit 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 17:06:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_unit")
public class ProductUnit extends Model<ProductUnit> {

	private static final long serialVersionUID = 1L;


	/**
	 * 产品单位id
	 */
	@TableId(value = "uid", type = IdType.AUTO)
	private Integer uid;

	/**
	 * 商品id
	 */
	private Integer productId;

	/**
	 * 单位名称
	 */
	private String unitName;

	/**
	 * 层级1-3
	 */
	private Integer level;

	/**
	 * 数量
	 */
	private Integer num;

	/**
	 * 创建时间
	 */
	@JsonIgnore
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.uid;
}
}
