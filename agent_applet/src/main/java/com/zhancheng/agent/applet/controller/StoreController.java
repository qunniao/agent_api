package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.StoreService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/stores")
public class StoreController {

    @Resource
    private StoreService storeService;

    @ApiOperation(value = "查询店铺详情")
    @ApiImplicitParam(name = "sid", value = "主键id")
    @GetMapping("/info/{sid}")
    public R<Store> info(@PathVariable("sid") Integer sid){

        return R.ok(storeService.info(sid));
    }

    @ApiOperation(value = "修改店铺")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "sid", value = "店铺id"),
                        @ApiImplicitParam(name = "storeName", value = "店铺名称"),
                        @ApiImplicitParam(name = "storeCover", value = "店铺头像"),
                        @ApiImplicitParam(name = "adCover", value = "店招图")
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Store store){

        return R.ok(storeService.update(store));
    }

}
