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
 * 店铺访客记录
 * zc_store_visitor 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_store_visitor")
public class StoreVisitor extends Model<StoreVisitor> {

	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户Id
	 */
	private Integer uid;

	/**
	 * 店铺Id
	 */
	private Integer storeId;

	/**
	 * 访问店铺归属人id
	 */
	private Integer managerId;

	/**
	 * 访问ip
	 */
	private String ip;

	/**
	 * 地区 
	 */
	private String region;

	/**
	 * 访问次数
	 */
	private Integer num;

	/**
	 * 是否删除 0:未删除; 1:删除 
	 */
	@TableLogic
	private Integer isDeleted;

	/**
	 * 访问时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
