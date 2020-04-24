package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductTypeService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.vo.ProductTypeVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.entity.ProductType;

import java.util.List;

/**
 * 产品类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

    @Override
    public IPage<ProductType> selectPage(PageDto<ProductType> pageDto, String sortType) {

        return baseMapper.queryTypePage(pageDto.getPage(), sortType);
    }

    @Override
    public List<ProductType> selectList() {
        List<ProductType> productTypes = baseMapper.queryTypeByList();

        System.err.println(productTypes);
        return  productTypes;
    }

    @Override
    public ProductType info(Integer tid) {

        ProductType productType = baseMapper.selectById(tid);
        if (ObjectUtil.isNull(productType)){
            throw new MyException(CodeMsg.PRODUCT_TYPE_NOT_EXISTED);
        }

        Integer count = baseMapper.countSubordinate(tid);

        if (ObjectUtil.isNotNull(count) && count > 0){
            productType.setIsSubordinate(true);
        }else{
            productType.setIsSubordinate(false);
        }

        return productType;
    }

    @Override
    public Boolean insert(ProductType productType) {
        return  baseMapper.insert(productType) > 0;
    }

    @Override
    public Boolean update(ProductType productType) {
        return baseMapper.updateById(productType) > 0;
    }

    @Override
    public Boolean delete(List<Integer> tids) {
        return baseMapper.deleteBatchIds(tids) > 0;
    }

}