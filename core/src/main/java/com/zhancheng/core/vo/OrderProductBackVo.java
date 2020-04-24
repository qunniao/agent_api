package com.zhancheng.core.vo;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderProductBackVo
 * @date 2019/10/30 11:11
 */
@Data
public class OrderProductBackVo {
    private Integer id;
    private Integer weight;
    private String cover;
    private String productNumber;
    private String productName;
    private Integer productNum;
    private BigDecimal productPrice;
}
