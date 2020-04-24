package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.*;
import com.zhancheng.core.entity.AgentStock;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.StockListVo;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project StockService
 * @date 2019/10/22 16:26
 */
public interface StockService {

    /**
     * 分页查询 产品库存列表
     * @param pageDto 分页数据
     * @param stockQueryDto 查询参数
     * @return IPage<Map>
     */
    IPage<StockListVo> queryStock(PageDto<Product> pageDto, StockQueryDto stockQueryDto);

    /**
     * 分页查询 sku库存列表
     * @param pageDto 分页数据
     * @param skuStockQueryDto 商品id
     * @return Map<String, Object>
     */
    Map<String, Object> querySkuStock(PageDto<Product> pageDto, SkuStockQueryDto skuStockQueryDto);

    /**
     * 调整库存
     * @param stockDto 库存数据
     * @return Boolean
     */
    Boolean adjustStock(StockDto stockDto);

    /**
     * 调整库存
     * @param stockDto 库存数据
     * @return Boolean
     */
    Boolean adjustSkuStock(StockDto stockDto);


    IPage queryAgentStock(PageDto<AgentStock> pageDto, AgentQueryListDto agentQueryListDto);
}
