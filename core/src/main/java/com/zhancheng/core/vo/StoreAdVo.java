package com.zhancheng.core.vo;

import com.zhancheng.core.entity.StoreAd;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project StoreAdVo
 * @date 2019/11/5 17:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StoreAdVo extends StoreAd {
    private String productName;
    private String productCover;
    private BigDecimal price;
}
