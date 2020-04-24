package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.StockHistoryService;
import com.zhancheng.backstage.service.StockService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.StockHistoryQueryDto;
import com.zhancheng.core.dto.StockQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.StockHistory;
import com.zhancheng.core.vo.StockHistoryVo;
import com.zhancheng.core.vo.StockListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @decription
 * @project StockHistoryController
 * @date 2019/10/23 17:01
 */
@Api(tags = "库存记录")
@RestController
@RequestMapping("/stockHistory")
public class StockHistoryController {

    @Resource
    private StockHistoryService stockHistoryService;

    @ApiOperation(value = "查询库存记录列表")
    @GetMapping("/page")
    public R<IPage<StockHistoryVo>> queryStock(PageDto<StockHistory> pageDto, StockHistoryQueryDto stockHistoryQueryDto){
        return R.ok(stockHistoryService.selectPage(pageDto, stockHistoryQueryDto));
    }
}
