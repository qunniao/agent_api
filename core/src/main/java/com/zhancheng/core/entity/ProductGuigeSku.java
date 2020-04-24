package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品sku表
 * zc_product_guige_sku 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_guige_sku")
public class ProductGuigeSku extends Model<ProductGuigeSku> {

    private static final long serialVersionUID = 1L;

    /**
     * skuid
     */
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Integer skuId;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 规格值id
     */
    private String sp1;

    /**
     * 规格值id
     */
    private String sp2;

    /**
     * 货号
     */
    private String productNumber;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 重量(克)
     */
    private Integer weight;

    /**
     * 图片
     */
    private String pic;

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

    @Override
    protected Serializable pkVal() {
        return this.skuId;
    }
}
