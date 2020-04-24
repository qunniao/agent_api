package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project SkuStockQueryDto
 * @date 2019/10/23 15:44
 */
@Data
public class SkuStockQueryDto {
    private Integer pid;
    private String sp1;
    private String sp2;
    private Integer sortType;
}
