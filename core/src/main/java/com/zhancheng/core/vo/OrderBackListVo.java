package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderBackListVo
 * @date 2019/10/30 16:43
 */
@Data
public class OrderBackListVo {

    private Integer oid;
    private Integer orderType;
    private Integer type;
    private String orderNumber;
    private BigDecimal totalPrice;
    private String contactName;
    private BigDecimal discount;
    private BigDecimal payMoney;
    private Integer payWay;
    private BigDecimal freight;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;
    private Integer orderState;
    private Integer isCloud;
    private String nick;
    private String nickname;
    private String phone;
    private Integer productNum;
    private Boolean deleted;

    private List<OrderProductBackVo> orderProductBackList;

}
