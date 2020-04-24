package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductImageService;
import com.zhancheng.core.dao.ProductImageMapper;
import com.zhancheng.core.entity.ProductImage;
import org.springframework.stereotype.Service;

/**
 * 产品主图和轮播图
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */

@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {

}