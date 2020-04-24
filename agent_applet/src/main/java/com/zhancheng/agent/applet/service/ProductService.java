package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * 产品spu表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
public interface ProductService extends IService<Product> {


    /**
     * 查询产品spu表详情
     * @param productQueryDto 商品查询信息
     * @return
     */
    List<Product> queryList(ProductQueryDto productQueryDto);
    /**
     * 查询产品spu表详情
     * @param pid 主键Id
     * @return
     */
    Map<String, Object>  info(Integer pid);

}

