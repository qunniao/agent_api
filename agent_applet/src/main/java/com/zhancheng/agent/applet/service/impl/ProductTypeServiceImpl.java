package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.ProductTypeService;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.entity.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

    @Override
    public List<Map<String, Object>> selectList() {

        return baseMapper.countTypeList();
    }

}