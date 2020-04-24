package com.zhancheng.core.vo;

import com.zhancheng.core.entity.StockHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BianShuHeng
 * @decription
 * @project StockHistoryVo
 * @date 2019/10/23 19:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StockHistoryVo extends StockHistory {
    private String productName;
    private String cover;
    private String pic;
    private String sp1;
    private String sp2;
}
