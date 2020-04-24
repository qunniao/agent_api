package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project RetailOrderInfoVo
 * @date 2019/11/1 15:43
 */
@Data
public class RetailOrderInfoVo {

    private Integer oid;
    private String orderNumber;
    private String contactName;
    private String contactPhone;
    private String contactAddress;
    private BigDecimal totalPrice;
    private BigDecimal freight;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    private Integer orderState;
    private String nickname;
    private String cover;
    private String phone;

    private List<OrderProductVo> orderProductList;
}
