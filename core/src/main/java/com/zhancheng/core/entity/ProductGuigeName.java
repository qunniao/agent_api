package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品规格名称
 * zc_product_guige_name 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Data
@Accessors(chain = true)
@TableName("zc_product_guige_name")
public class ProductGuigeName extends Model<ProductGuigeName> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId(value = "nid", type = IdType.AUTO)
    private Integer nid;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 属性名
     */
    private String gName;

    /**
     * 等级
     */
    private Integer level;

    @TableField(exist = false)
    @ApiModelProperty(name = "productGuigeValues", value = "规格值数组", example = "1")
    private ProductGuigeValue[] productGuigeValues = new ProductGuigeValue[]{};

    @Override
    protected Serializable pkVal() {
        return this.nid;
    }
}
