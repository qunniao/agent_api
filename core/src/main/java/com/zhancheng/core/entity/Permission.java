package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 后台功能权限
 * zc_permission 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_permission")
@ApiModel(value = "后台功能权限")
public class Permission extends Model<Permission> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "pid", value = "功能权限id")
	@TableId(value = "pid", type = IdType.AUTO)
	private Integer pid;

	@ApiModelProperty(name = "mid", value = "菜单id")
	private String mid;

	@ApiModelProperty(name = "controller", value = "控制器名")
	private String controller;

	@ApiModelProperty(name = "method", value = "方法名")
	private String method;

	@ApiModelProperty(name = "remark", value = "权限描述")
	private String remark;

	@Override
	protected Serializable pkVal() {
		return this.pid;
}
}
