package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品参数表
 * zc_product_param 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_param")
public class ProductParam extends Model<ProductParam> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否删除 0:未删除; 1:删除
     */
    @JsonIgnore
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    private Date gmtModified;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
