package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StockDtp
 * @date 2019/10/21 17:32
 */
@Data
public class StockQueryDto {

    private String search;
    private Integer typeId;
    private Integer productType;
    private Integer statusType;
    private Integer sortType;
}
