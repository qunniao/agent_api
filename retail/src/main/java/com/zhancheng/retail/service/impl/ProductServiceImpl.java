package com.zhancheng.retail.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.ProductImageMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductImage;
import com.zhancheng.retail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductServiceImpl
 * @date 2019/10/26 16:08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public List<Product> queryList(ProductQueryDto productQueryDto) {
        return baseMapper.queryList(productQueryDto);
    }

    @Override
    public Map<String, Object> info(Integer pid) {

        Map<String, Object> map = productMapper.queryDetails(pid);

        if (CollectionUtil.isEmpty(map)) {

            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        List<ProductImage> productImageList = productImageMapper.selectList(new QueryWrapper<ProductImage>()
                .eq("pid", pid)
                .eq("is_deleted", 0).orderByDesc("sort"));

        map.put("productImageList", productImageList);
        Object sidObject = map.get("sid");

        if (ObjectUtil.isNotNull(sidObject)) {
            Integer sid = (Integer) sidObject;

            Integer count = productMapper.countStoreProductNum(sid);
            // 类型id
            String typeId = productMapper.queryType(sid);

            map.put("typeId", typeId);
            map.put("productNum", count);
        }
        return map;
    }
}
