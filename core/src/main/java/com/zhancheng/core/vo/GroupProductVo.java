package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project GroupProductVo
 * @date 2019/11/7 16:24
 */
@Data
public class GroupProductVo {
    private Integer gpId;
    private Integer pid;
    private Integer skuId;
    private Integer num;
    private String productName;
    private Integer status;
    private Integer availableStock;
    private String cover;
    private BigDecimal price;
    private BigDecimal stockPrice;
}
