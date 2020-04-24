package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品组合套餐
 * zc_group_package 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_group_package")
public class GroupPackage extends Model<GroupPackage> {

	private static final long serialVersionUID = 1L;


	/**
	 * 产品组合套餐id
	 */
	@TableId(value = "gid", type = IdType.AUTO)
	private Integer gid;

	/**
	 * 组合套餐名称
	 */
	private String title;

	/**
	 * 组合套餐图片
	 */
	private String cover;

	/**
	 * 套餐价格
	 */
	private BigDecimal price;

	/**
	 * 运费类型 运费：0.包邮1.按默认设置 自定义
	 */
	private Integer freightType;

	/**
	 * 运费价格
	 */
	private BigDecimal freight;

	/**
	 * 自定义折扣 0.默认折扣
	 */
	private BigDecimal discount;

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

	@TableField(exist = false)
	private List<GroupProduct> groupProductList;

	@Override
	protected Serializable pkVal() {
		return this.gid;
}
}
