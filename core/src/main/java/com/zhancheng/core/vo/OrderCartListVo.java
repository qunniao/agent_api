package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderCartListVo
 * @date 2019/10/25 17:06
 */
@Data
public class OrderCartListVo {

    private Integer cid;
    private Integer skuId;
    private Integer pid;
    private String cover;
    private String productName;
    private Integer productNum;
    private BigDecimal productPrice;
    private Integer weight;
    private String sp1;
    private String sp2;
}
