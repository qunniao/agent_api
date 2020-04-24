package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductSkuListVo
 * @date 2019/10/21 15:07
 */
@Data
public class ProductSkuListVo {
    private Integer skuId;
    private Integer pid;
    private String sp1;
    private String sp2;
    private String productNumber;
    private BigDecimal price;
    private Integer weight;
    private String pic;
}
