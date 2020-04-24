package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 店铺
 * zc_store 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_store")
public class Store extends Model<Store> {

	private static final long serialVersionUID = 1L;


	/**
	 *  店铺id
	 */
	@TableId(value = "sid", type = IdType.AUTO)
	private Integer sid;

	/**
	 * 代理人id
	 */
	private Integer agentId;
	/**
	 * 店铺名称
	 */
	private String storeName;

	/**
	 * 店铺头像
	 */
	private String storeCover;

	/**
	 * 店招图
	 */
	private String adCover;

	/**
	 * 店铺简介
	 */
	private String storeIntro;

	/**
	 * 零售小店开关 0:关1:开 
	 */
	private Integer status;

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
		return this.sid;
}
}
