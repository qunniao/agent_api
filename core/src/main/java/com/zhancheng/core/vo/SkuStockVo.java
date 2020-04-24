package com.zhancheng.core.vo;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project SkuStockVo
 * @date 2019/10/23 11:41
 */
@Data
public class SkuStockVo {
    private Integer skuId;
    private String sp1;
    private String sp2;
    private String pic;
    private Integer totalStock;
    private Integer availableStock;
    private Integer lockStock;
    private Integer excessStock;
}
