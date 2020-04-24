package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.backstage.service.StockService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.*;
import com.zhancheng.core.entity.AgentStock;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.StockListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project StockController
 * @date 2019/10/21 17:29
 */
@Api(tags = "库存管理")
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @ApiOperation(value = "查询库存列表")
    @GetMapping("/page")
    public R<IPage<StockListVo>> queryStock(PageDto<Product> pageDto, StockQueryDto stockQueryDto){
          return R.ok(stockService.queryStock(pageDto, stockQueryDto));
    }

    @ApiOperation(value = "查询sku库存列表")
    @GetMapping("/sku/page")
    public R<Map<String, Object>> querySkuStock(PageDto<Product> pageDto, SkuStockQueryDto skuStockQueryDto){
          return R.ok(stockService.querySkuStock(pageDto, skuStockQueryDto));
    }

    @ApiOperation(value = "调整商品库存")
    @PostMapping("/adjustStock")
    public R<Boolean> adjustStock(@RequestBody StockDto stockDto){
          return R.ok(stockService.adjustStock(stockDto));
    }

    @ApiOperation(value = "调整sku库存")
    @PostMapping("/adjustSkuStock")
    public R<Boolean> adjustSkuStock(@RequestBody StockDto stockDto){
          return R.ok(stockService.adjustSkuStock(stockDto));
    }

    @ApiOperation(value = "查询代理库存")
    @PostMapping("/queryAgentStock")
    public R<IPage> queryAgentStock(PageDto<AgentStock> pageDto, AgentQueryListDto agentQueryListDto){
          return R.ok(stockService.queryAgentStock(pageDto,agentQueryListDto));
    }
}
