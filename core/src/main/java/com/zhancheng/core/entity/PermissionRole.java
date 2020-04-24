package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 后台功能权限分配
 * zc_permission_role 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_permission_role")
@ApiModel(value = "后台功能权限分配")
public class PermissionRole extends Model<PermissionRole> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "功能权限分配id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "rid", value = "角色id")
	private Integer rid;

	@ApiModelProperty(name = "pid", value = "权限id")
	private Integer pid;

	@TableField(exist =false)
	private Permission permission;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
