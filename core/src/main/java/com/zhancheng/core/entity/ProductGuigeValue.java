package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品规格值
 * zc_product_guige_value 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_guige_value")
public class ProductGuigeValue extends Model<ProductGuigeValue> {

    private static final long serialVersionUID = 1L;


    /**
     * 属性值id
     */
    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    /**
     * 属性名id
     */
    private Integer nid;

    /**
     * 规格值
     */
    private String gValue;

    @Override
    protected Serializable pkVal() {
        return this.vid;
    }
}
