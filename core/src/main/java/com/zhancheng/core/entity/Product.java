package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhancheng.core.vo.ProductParamVo;
import com.zhancheng.core.vo.ProductTypeVo;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品spu表
 * zc_product 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "pid", type = IdType.AUTO)
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
     * 店铺id
     */
    private Integer sid;

    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品主图
     */
    private String cover;

    /**
     * 产品描述
     */
    private String productIntro;

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
     * 商品重量为不能超过9个字符的正整数
     */
    private Integer weight;

    /**
     * 是否推荐。0不推荐，1推荐
     */
    private Integer isRecommend;

    /**
     * 总库存
     */
    private Integer totalStock;

    /**
     * 可用库存
     */
    private Integer availableStock;

    /**
     * 临时锁定库存
     */
    private Integer lockStock;

    /**
     * 超额库存
     */
    private Integer excessStock;

    /**
     * 代理云库存
     */
    private Integer cloudStock;

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
     * 实际销量，确认收货后增加，退换货减少
     */
    private Integer realSales;

    /**
     * 流水销量，提交订单后增加
     */
    private Integer flowSales;

    /**
     * 代理购买方式0.不代理1.物流进货2.云仓库储货3.物流和云仓库
     */
    private Integer agentBuy;

    /**
     * 邀请一次性奖励金额
     */
    private BigDecimal inviteReward;

    /**
     * 商品排序
     */
    private Integer sort;

    /**
     * 定时上架时间戳 0.否
     */
    private Integer putawayTime;

    /**
     * 定时下架时间戳 0.否
     */
    private Integer soldoutTime;

    /**
     * 是否有sku
     */
    private Boolean isSku;

    /**
     * 产品状态：0上架中，-1已下架
     */
    private Integer status;

    /**
     * 是否删除 0:未删除; 1:删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    @TableField(exist = false)
    private List<ProductTypeVo> productTypeList;

    @Override
    protected Serializable pkVal() {
        return this.pid;
    }
}
