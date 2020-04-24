package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 库存历史
 * zc_stock_history 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-22 14:13:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_stock_history")
public class StockHistory extends Model<StockHistory> {

	private static final long serialVersionUID = 1L;


	/**
	 * 库存历史id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 商品id
	 */
	private Integer pid;
	/**
	 * 规格id
	 */
	private Integer skuId;

	/**
	 * 1.总部2.代理
	 */
	private Integer userType;

	/**
	 * 后台管理操作人id
	 */
	private Integer operatorId;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 代理人名称
	 */
	private String agentName;

	/**
	 * 代理人手机号
	 */
	private String agentPhone;

	/**
	 * 代理人等级id
	 */
	private Integer agentLevel;

	/**
	 * 变更项类型：1.采购实体库存2.零售发货3.给下级发货4.云库存提货5.总部调整6.代理调整7.零售发货8.提货发货9.转采购发货10.云库存代发11.手动调整12.划拨13.实体商品换货14.云库存换货15.零售出库

	 */
	private Integer type;

	/**
	 * 增减方式：1.增加2.减少
	 */
	private Integer changeType;

	/**
	 * 变更库存数量
	 */
	private Integer num;

	/**
	 * 当前库存数
	 */
	private Integer stock;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 库存类型1.实体2.云库存
	 */
	private Integer stockType;

	/**
	 * 订单类型：1.采购2.零售3.提货4.换货
	 */
	private Integer orderType;

	/**
	 * 说明
	 */
	private String intro;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
