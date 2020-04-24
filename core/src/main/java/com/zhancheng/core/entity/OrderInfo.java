package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.vo.OrderProductVo;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单表
 * zc_order_info 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-28 11:53:16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_order_info")
public class OrderInfo extends Model<OrderInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId(value = "oid", type = IdType.AUTO)
	private Integer oid;

	/**
	 * 类型：1.总部订单2.代理订单
	 */
	private Integer type;

	/**
	 * 订单类型：1.采购2.零售3.提货4.内购5.换货
	 */
	private Integer orderType;

	/**
	 * 用户id
	 */
	private Integer uid;

	/**
	 * 代理人id
	 */
	private Integer agentId;

	/**
	 * 店铺所属人id
	 */
	private Integer storePersonId;

	/**
	 * 订单号
	 */
	private String orderNumber;

	/**
	 * 联系人
	 */
	private String contactName;

	/**
	 * 联系电话
	 */
	private String contactPhone;

	/**
	 * 收货地址
	 */
	private String contactAddress;

	/**
	 * 支付平台  0为余额支付，1为支付宝，2为微信
	 */
	private Integer payPlatform;

	/**
	 * 第三方支付单号
	 */
	private String payNumber;

	/**
	 * 配送方式1.快递2.自提3.无需物流
	 */
	private Integer sendWay;

	/**
	 * 支付方式 1.打款凭证2.线上支付
	 */
	private Integer payWay;

	/**
	 * 总价格
	 */
	private BigDecimal totalPrice;

	/**
	 * 实际付款
	 */
	private BigDecimal payMoney;

	/**
	 * 支付时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;

	/**
	 * 运费
	 */
	private BigDecimal freight;

	/**
	 * 优惠金额
	 */
	private BigDecimal discount;

	/**
	 * 是否云订单：1.是0.否
	 */
	private Integer isCloud;

	/**
	 * 是否拆单：1.是0.否
	 */
	private Integer isOpen;

	/**
	 * 是否首进
	 */
	private Integer isFirst;

	/**
	 * 是否转代发
	 */
	private Integer isForward;

	/**
	 * 是否云代发
	 */
	private Integer isCloudForward;

	/**
	 * 凭证状态：1.已传凭证，待总部审核2.凭证已通过3.凭证已驳回
	 */
	private Integer voucherState;
	/**
	 * 订单状态：-1.已删除0.已关闭1.待确认2.待付款3.待总部处理4.待发货5.待收货6.待评价7.已完成
	 */
	private Integer orderState;

	/**
	 * 留言
	 */
	private String remark;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@TableField(exist = false)
	private List<OrderProductVo> orderProductList;

	@Override
	protected Serializable pkVal() {
		return this.oid;
}
}
