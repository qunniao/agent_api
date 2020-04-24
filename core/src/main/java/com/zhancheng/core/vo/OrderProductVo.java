package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderProductVo
 * @date 2019/10/28 17:39
 */
@Data
public class OrderProductVo {
    private Integer id;
    private String sp1;
    private String sp2;
    private String cover;
    private Integer sid;
    private String storeCover;
    private String storeName;
    private String productName;
    private Integer productNum;
    private BigDecimal productPrice;
}
