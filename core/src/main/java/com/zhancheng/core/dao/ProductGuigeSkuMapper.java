package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.SkuStockQueryDto;
import com.zhancheng.core.vo.StockCountVo;
import com.zhancheng.core.entity.ProductGuigeSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ProductSkuListVo;
import com.zhancheng.core.vo.SkuStockVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品sku表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Repository
public interface ProductGuigeSkuMapper extends BaseMapper<ProductGuigeSku> {

    /**
     * 根据商品id查询 sku列表
     * @param pid 商品id
     * @return
     */
    List<ProductSkuListVo> queryList(Integer pid);

    /**
     * 根据商品id查询 sku列表
     * @param page 分页数据
     * @param pid 商品id
     * @return
     */
    IPage<ProductSkuListVo> queryPage(Page page, @Param("pid") Integer pid);

    /**
     * 根据商品id查询 sku列表
     * @param page 分页数据
     * @param skuStockQueryDto 查询条件
     * @return
     */
    IPage<SkuStockVo> queryStock(Page page, @Param("query") SkuStockQueryDto skuStockQueryDto);

    StockCountVo countStock(Integer pid);
}
