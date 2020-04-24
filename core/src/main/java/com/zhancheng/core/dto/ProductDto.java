package com.zhancheng.core.dto;

import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductDto
 * @date 2019/10/17 16:03
 */
@Data
public class ProductDto {

    @ApiModelProperty(name = "pid", value = "商品id", example = "1")
    private Integer pid;
    @ApiModelProperty(name = "sid", value = "店铺id", example = "1")
    private Integer sid;
    @ApiModelProperty(name = "productType", value = "商品类型0.常规商品1.内购商品", example = "1")
    private Integer productType;
    @ApiModelProperty(name = "productName", value = "商品名称", example = "1")
    private String productName;
    @ApiModelProperty(name = "cover", value = "商品主图", example = "1")
    private String cover;
    @ApiModelProperty(name = "productNumber", value = "商品货号", example = "1")
    private String productNumber;
    @ApiModelProperty(name = "content", value = "手机端详情", example = "1")
    private String content;
    @ApiModelProperty(name = "price", value = "商品零售价", example = "1")
    private BigDecimal price;
    @ApiModelProperty(name = "marketPrice", value = "商品市场价格", example = "1")
    private BigDecimal marketPrice;
    @ApiModelProperty(name = "stockPrice", value = "进货价", example = "1")
    private BigDecimal stockPrice;
    @ApiModelProperty(name = "weight", value = "商品重量(克)", example = "1")
    private Integer weight;
    @ApiModelProperty(name = "showStock", value = "下单时是否显示库存", example = "1")
    private Boolean showStock;
    @ApiModelProperty(name = "sort", value = "商品排序：排序数字高的商品排列在前", example = "1")
    private Integer sort;
    @ApiModelProperty(name = "sales", value = "基础销量：系统虚拟的销量基数", example = "1")
    private Integer sales;
    @ApiModelProperty(name = "retailFreightId", value = "零售运费模板", example = "1")
    private Integer retailFreightId;
    @ApiModelProperty(name = "agentFreightId", value = "代理运费模板", example = "1")
    private Integer agentFreightId;
    @ApiModelProperty(name = "cloudFreightId", value = "云仓库提货运费模板", example = "1")
    private Integer cloudFreightId;
    @ApiModelProperty(name = "isUniformFreight", value = "是否统一运费：0.否1.是", example = "1")
    private Integer isUniformFreight;
    @ApiModelProperty(name = "agentBuy", value = "代理购买方式0.不代理1.物流进货2.云仓库储货3.物流和云仓库", example = "1")
    private Integer agentBuy;
    @ApiModelProperty(name = "putawayTime", value = "定时上架时间戳 0.否", example = "1")
    private Integer putawayTime;
    @ApiModelProperty(name = "soldoutTime", value = "定时下架时间戳 0.否", example = "1")
    private Integer soldoutTime;
    @ApiModelProperty(name = "status", value = "产品状态：0上架中，-1已下架", example = "1")
    private Integer status;
    @ApiModelProperty(name = "inviteReward", value = "邀请一次性奖励金额", example = "1")
    private BigDecimal inviteReward;

    @ApiModelProperty(name = "productImageDtos", value = "商品图片", example = "1")
    private ProductImageDto[] productImageDtos = new ProductImageDto[]{};
    @ApiModelProperty(name = "typeIds", value = "商品分类", example = "1")
    private Integer[] typeIds = new Integer[]{};
    @ApiModelProperty(name = "productParamDtos", value = "商品属性", example = "1")
    private ProductParamDto[] productParamDtos = new ProductParamDto[]{};
    @ApiModelProperty(name = "units", value = "商品箱规设置", example = "1")
    private UnitDto[] units = new  UnitDto[]{};

    @ApiModelProperty(name = "productGuigeNames", value = "规格名称数组", example = "1")
    private ProductGuigeName[] productGuigeNames = new ProductGuigeName[]{};
    @ApiModelProperty(name = "standardDtos", value = "商品规格", example = "1")
    private StandardDto[] standardDtos = new StandardDto[]{};
}
