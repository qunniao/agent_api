package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.ProductDto;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductVo;

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
     * 分页查询产品spu表列表
     * @param pageDto 分页参数
     * @param productQueryDto 查询参数
     * @return Map<String,Object>
     */
    Map<String,Object> selectPage(PageDto<Product> pageDto, ProductQueryDto productQueryDto);

    /**
     * 查询产品spu表详情
     * @param pid 主键Id
     * @return
     */
    ProductVo info(Integer pid);

    /**
     * 添加产品spu表
     * @param productDto 产品spu表数据
     * @return Boolean
     */
    Boolean insert(ProductDto productDto);

    /**
     * 修改产品spu表
     * @param productDto 产品spu表数据
     * @return Boolean
     */
    Boolean update(ProductDto productDto);

    /**
     * 批量删除产品spu表
     * @param pids 产品spu表id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> pids);

    /**
     * 查询货号是否存在
     * @param number 商品货号
     * @return Boolean
     */
    Boolean isExist(String number);

}

