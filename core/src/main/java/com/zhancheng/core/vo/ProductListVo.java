package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductListVo
 * @date 2019/10/18 11:43
 */
@Data
public class ProductListVo {
    @ApiModelProperty(name = "pid", value = "商品id")
    private Integer pid;
    @ApiModelProperty(name = "productType", value = "商品类型0.常规商品1.内购商品")
    private Integer productType;
    @ApiModelProperty(name = "productName", value = "商品市场价格")
    private String productName;
    @ApiModelProperty(name = "cover", value = "商品主图")
    private String cover;
    @ApiModelProperty(name = "productNumber", value = "商品货号")
    private String productNumber;
    @ApiModelProperty(name = "typeName", value = "商品类型")
    private String typeName;
    @ApiModelProperty(name = "standardNum", value = "规格数量")
    private Integer standardNum;
    @ApiModelProperty(name = "price", value = "商品价格")
    private BigDecimal price;
    @ApiModelProperty(name = "marketPrice", value = "市场价")
    private BigDecimal marketPrice;
    @ApiModelProperty(name = "stockPrice", value = "进货价")
    private BigDecimal stockPrice;
    @ApiModelProperty(name = "totalStock", value = "总库存")
    private Integer totalStock;
    @ApiModelProperty(name = "availableStock", value = "可用库存")
    private Integer availableStock;
    @ApiModelProperty(name = "retailFreightId", value = "零售运费模板id")
    private Integer retailFreightId;
    @ApiModelProperty(name = "agentFreightId", value = "代理运费模板id")
    private Integer agentFreightId;
    @ApiModelProperty(name = "cloudFreightId", value = "云仓库提货运费模板id")
    private Integer cloudFreightId;
    @ApiModelProperty(name = "sales", value = "基础销量")
    private Integer sales;
    @ApiModelProperty(name = "realSales", value = "实际销量")
    private Integer realSales;
    @ApiModelProperty(name = "flowSales", value = "流水销量")
    private Integer flowSales;
    @ApiModelProperty(name = "inviteReward", value = "邀请一次性奖励金额")
    private BigDecimal inviteReward;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;
    @ApiModelProperty(name = "status", value = "产品状态：0上架中，-1已下架")
    private Integer status;
}
