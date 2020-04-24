package com.zhancheng.core.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderInfoDto
 * @date 2019/10/28 14:16
 */
@Data
public class OrderInfoDto {

    /**
     * 类型：1.总部订单2.代理订单
     */
    private Integer inviter;

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
     * 店铺id
     */
    private Integer storeId;

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
     * 配送方式1.快递2.自提3.无需物流
     */
    private Integer sendWay;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    @ApiModelProperty(name = "discount", value = "优惠金额")
    private BigDecimal discount = new BigDecimal("0");

    /**
     * 运费
     */
    private BigDecimal freight = new BigDecimal("0");

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
     * 留言
     */
    private String remark;

    /**
     * 店铺所属人id
     */
    private Integer storePersonId;
    /**
     * 购物车id集合
     */
    private List<Integer> cidList;

    private List<OrderProductDto> orderProductDtoList;

}
