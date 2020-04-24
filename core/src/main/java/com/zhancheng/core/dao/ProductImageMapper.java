package com.zhancheng.core.dao;

import com.zhancheng.core.entity.ProductImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产品主图和轮播图
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Repository
public interface ProductImageMapper extends BaseMapper<ProductImage> {
	
}
