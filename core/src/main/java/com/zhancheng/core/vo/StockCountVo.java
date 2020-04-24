package com.zhancheng.core.vo;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StockCountVo
 * @date 2019/10/29 18:36
 */
@Data
public class StockCountVo {
    private Integer totalStock;
    private Integer availableStock;
    private Integer lockStock;
    private Integer excessStock;
    private Integer cloudStock;
}
