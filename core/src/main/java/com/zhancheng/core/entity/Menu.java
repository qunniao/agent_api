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
 * 后台菜单
 * zc_menu 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_menu")
@ApiModel(value = "后台菜单")
public class Menu extends Model<Menu> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "mid", value = "菜单id")
	@TableId(value = "mid", type = IdType.AUTO)
	private Integer mid;

	@ApiModelProperty(name = "fid", value = "父级id")
	private Integer fid;

	@ApiModelProperty(name = "name", value = "菜单名")
	private String name;

	@ApiModelProperty(name = "icon", value = "图标")
	private String icon;

	@ApiModelProperty(name = "url", value = "路径")
	private String url;

	@ApiModelProperty(name = "sort", value = "排序")
	private Integer sort;

	@Override
	protected Serializable pkVal() {
		return this.mid;
}
}
