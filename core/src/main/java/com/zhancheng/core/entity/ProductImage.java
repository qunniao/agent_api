package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品主图和轮播图
 * zc_product_image 实体类
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_product_image")
public class ProductImage extends Model<ProductImage> {

    private static final long serialVersionUID = 1L;


    /**
     * 产品图片id
     */
    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 图片路径
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

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

    @Override
    protected Serializable pkVal() {
        return this.bid;
    }
}
