package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.AgentStockProductService;
import com.zhancheng.core.dto.AgentStockQueryDto;
import com.zhancheng.core.vo.AgentStockProductVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;

import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.AgentStockProduct;


import javax.annotation.Resource;


/**
 * 代理库存商品
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-02 11:47:42
 */
@Api(tags = "代理库存商品")
@RestController
@RequestMapping("/agentStockProducts")
public class AgentStockProductController {

    @Resource
    private AgentStockProductService agentStockProductService;

    @ApiOperation(value = "代理端分页查询代理库存商品列表")
    @GetMapping("/page")
    public R<IPage<AgentStockProductVo>> queryPage(PageDto<AgentStockProduct> pageDto, AgentStockQueryDto agentStockQueryDto){

        return R.ok(agentStockProductService.queryPage(pageDto, agentStockQueryDto));
    }
}
