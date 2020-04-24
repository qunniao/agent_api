package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductQueryDto
 * @date 2019/10/18 14:49
 */
@Data
public class ProductQueryDto {

    @ApiModelProperty(name = "productType", value = "商品类型0.常规商品1.内购商品", example = "1")
    private Integer productType;
    @ApiModelProperty(name = "search", value = "搜索条件", example = "1")
    private String search;
    @ApiModelProperty(name = "startTime", value = "开始时间", example = "1")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "结束时间", example = "1")
    private String endTime;
    @ApiModelProperty(name = "productNumber", value = "规格货号", example = "1")
    private Integer productNumber;
    @ApiModelProperty(name = "typeId", value = "商品分类id", example = "1")
    private Integer typeId;
    @ApiModelProperty(name = "minStore", value = "最小库存", example = "1")
    private Integer minStore;
    @ApiModelProperty(name = "maxStore", value = "最大库存", example = "1")
    private Integer maxStore;
    @ApiModelProperty(name = "minPrice", value = "价格区间:最小价格", example = "1")
    private String minPrice;
    @ApiModelProperty(name = "maxPrice", value = "价格区间:最高价格", example = "1")
    private String maxPrice;
    @ApiModelProperty(name = "minSales", value = "最小销量", example = "1")
    private Integer minSales;
    @ApiModelProperty(name = "maxSales", value = "最大销量", example = "1")
    private Integer maxSales;
    @ApiModelProperty(name = "statusType", value = "状态类型 1：出售中", example = "1")
    private String statusType;
    @ApiModelProperty(name = "sortType", value = "排序类型：1：价格升序2：价格降序 按顺序往后排", example = "1")
    private Integer sortType;
    @ApiModelProperty(name = "freightId", value = "运费id", example = "1")
    private Integer freightId;
}
