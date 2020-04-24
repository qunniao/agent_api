package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.dto.StockDto;
import com.zhancheng.core.dto.StockQueryDto;
import com.zhancheng.core.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ProductCountVo;
import com.zhancheng.core.vo.ProductListVo;
import com.zhancheng.core.vo.ProductVo;
import com.zhancheng.core.vo.StockListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品spu表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 分页查询产品列表
     * @param page 分页数据
     * @param productQueryDto 查询数据
     * @return ProductListVo
     */
    IPage<ProductListVo> queryPage(Page page, @Param("query") ProductQueryDto productQueryDto);

    List<Product> queryList(@Param("query") ProductQueryDto productQueryDto);

    /**
     *  统计数量
     * @param productQueryDto 产品查询参数
     * @return
     */
    ProductCountVo countProduct(@Param("query") ProductQueryDto productQueryDto);

    /**
     * 查询产品详情
     * @param pid 产品id
     * @return
     */
    ProductVo queryInfo(Integer pid);

    /**
     * 查询商品库存
     * @param page 分页数据
     * @param stockQueryDto 查询数据
     * @return
     */
    IPage<StockListVo> queryStock(Page page, @Param("query") StockQueryDto stockQueryDto);

    /**
     * 根据运费id统计商品数量
     * @param fid
     * @return
     */
    Integer countFreightNum(@Param("fid") Integer fid);

    /**
     * 小程序查询商品详情
     * @param pid
     * @return
     */
    Map<String, Object> queryDetails(@Param("pid") Integer pid);

    /**
     * 统计店铺商品数量
     * @param sid 店铺id
     * @return Integer
     */
    Integer countStoreProductNum(@Param("sid") Integer sid);

    /**
     * 统计店铺商品数量
     * @param sid 店铺id
     * @return Integer
     */
    String queryType(@Param("sid") Integer sid);
}
