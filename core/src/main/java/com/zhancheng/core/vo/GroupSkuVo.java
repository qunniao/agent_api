package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project GroupSkuVo
 * @date 2019/11/7 16:29
 */
@Data
public class GroupSkuVo {
    private Integer skuId;
    private String sp1;
    private String sp2;
    private BigDecimal price;
}
