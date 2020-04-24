package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderListVo
 * @date 2019/10/28 16:57
 */
@Data
public class OrderListVo {
    private Integer oid;
    private Integer uid;
    private Integer type;
    private Integer agentId;
    private Integer orderType;
    private String orderNumber;
    private BigDecimal totalPrice;
    private Boolean isCloud;
    private Integer orderState;

}
