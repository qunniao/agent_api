package com.zhancheng.core.vo;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StockListVo
 * @date 2019/10/22 11:04
 */
@Data
public class StockListVo {
    private Integer pid;
    private Integer productType;
    private String productName;
    private Boolean isSku;
    private Integer totalStock;
    private Integer availableStock;
    private Integer lockStock;
    private Integer excessStock;
    private String typeName;
}
