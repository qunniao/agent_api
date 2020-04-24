package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductImage;
import com.zhancheng.core.entity.ProductParam;
import com.zhancheng.core.entity.ProductUnit;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductVo
 * @date 2019/10/21 14:25
 */
@Data
@Accessors(chain = true)
public class ProductVo {

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 商品类型0.常规商品1.内购商品
     */
    private Integer productType;

    /**
     * 商品货号
     */
    private String productNumber;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品主图
     */
    private String cover;

    /**
     * 商品零售价
     */
    private BigDecimal price;

    /**
     * 商品市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 进货价
     */
    private BigDecimal stockPrice;

    /**
     * 可用库存
     */
    private Integer availableStock;

    /**
     * 商品重量为不能超过9个字符的正整数
     */
    private Integer weight;

    /**
     * 下单时是否显示库存1.是0.否
     */
    private Integer showStock;

    /**
     * 手机端详情
     */
    private String content;

    /**
     * 零售运费模板id
     */
    private Integer retailFreightId;

    /**
     * 代理运费模板id
     */
    private Integer agentFreightId;

    /**
     * 云仓库提货运费模板id
     */
    private Integer cloudFreightId;

    /**
     * 是否统一运费：0.否1.是
     */
    private Integer isUniformFreight;

    /**
     * 基础销量：系统虚拟的销量基数
     */
    private Integer sales;

    /**
     * 代理购买方式0.不代理1.物流进货2.云仓库储货3.物流和云仓库
     */
    private Integer agentBuy;

    /**
     * 邀请一次性奖励金额
     */
    private BigDecimal inviteReward;

    /**
     * 是否有sku
     */
    private Boolean isSku;

    /**
     * 定时上架时间戳 0.否
     */
    private Integer putawayTime;

    /**
     * 定时下架时间戳 0.否
     */
    private Integer soldoutTime;

    /**
     * 产品状态：0上架中，-1已下架
     */
    private Integer status;

    /**
     * 分类列表
     */
    private List<ProductTypeVo> productTypeList;

    /**
     * 图片列表
     */
    private List<ProductImage> productImageList;

    /**
     * 商品属性集合
     */
    private List<ProductParam> productParamList;

    /**
     * 商品箱规
     */
    private List<ProductUnit> productUnitList;

    /**
     * 规格名称
     */
    private List<ProductGuigeName> productGuigeNameList;

    /**
     * sku
     */
    private List<ProductSkuListVo> productSkuList;

}
