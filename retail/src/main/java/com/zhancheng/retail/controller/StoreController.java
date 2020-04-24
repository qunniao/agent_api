package com.zhancheng.retail.controller;

import com.zhancheng.core.constant.R;
import com.zhancheng.retail.service.StoreService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import com.zhancheng.core.entity.Store;

import javax.annotation.Resource;


/**
 * 店铺
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @ApiOperation(value = "查询店铺详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id"),
            @ApiImplicitParam(name = "uid", value = "用户id", required = true)
    })
    @GetMapping("/info")
    public R<Store> info(Integer agentId, Integer uid){

        return R.ok(storeService.info(agentId, uid));
    }

}
