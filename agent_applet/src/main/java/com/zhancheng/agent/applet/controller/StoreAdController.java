package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.StoreAdService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.StoreAd;
import com.zhancheng.core.vo.StoreAdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 店铺广告商品
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 11:26:07
 */
@Api(tags = "店铺广告商品")
@RestController
@RequestMapping("/storeAds")
public class StoreAdController {

    @Resource
    private StoreAdService storeAdService;

    @ApiOperation(value = "查询店铺广告商品列表")
    @ApiImplicitParam(name = "type", value = "类型1.置顶商品2.新品3.明星产品4.店铺首页")
    @GetMapping("/info/{type}")
    public R<List<StoreAdVo>> info(@PathVariable("type") Integer type){

        return R.ok(storeAdService.queryInfo(type));
    }

}
