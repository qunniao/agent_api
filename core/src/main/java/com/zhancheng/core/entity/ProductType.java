package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品类目
 * zc_product_type 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_type")
public class ProductType extends Model<ProductType> {

    private static final long serialVersionUID = 1L;


    /**
     * 类目id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    /**
     * 类目父亲id(0表示一级类目)
     */
    private Integer pid;

    /**
     * 关联属性组id
     */
    private Integer pgId;

    /**
     * 类目名称
     */
    private String typeName;

    /**
     * 图片
     */
    private String cover;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否删除 0:未删除; 1:删除
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 是否隐藏 0:未隐藏; 1:已隐藏，推广商品时不可见
     */
    private Integer isHidden;

    /**
     * 是否有下级
     */
    @TableField(exist = false)
    private Boolean isSubordinate;

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
    private List<ProductType> productTypeList;

    @Override
    protected Serializable pkVal() {
        return this.tid;
    }
}
