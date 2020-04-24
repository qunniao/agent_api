package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.ProductType;

import java.util.List;
import java.util.Map;

/**
 * 产品类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
public interface ProductTypeService extends IService<ProductType> {

    /**
     * 分页查询产品类目列表
     * @return List<ProductTypeVo>
     */
    List<Map<String, Object>> selectList();

}

