package com.zhancheng.backstage.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.GroupPackageService;
import com.zhancheng.core.vo.GroupPackageVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.entity.GroupPackage;

import javax.annotation.Resource;


/**
 * 产品组合套餐
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
@Api(tags = "产品组合套餐")
@RestController
@RequestMapping("/groupPackages")
public class GroupPackageController {

    @Resource
    private GroupPackageService groupPackageService;

    @ApiOperation(value = "查询等级id查询套餐列表")
    @GetMapping("/list/levelId")
    public R<List<GroupPackage>> queryList(Integer levelId) {

        return R.ok(groupPackageService.queryList(levelId));
    }

    @ApiOperation(value = "查询套餐组合id查询套餐详情")
    @ApiImplicitParam(name = "gid", value = "组合套餐id")
    @GetMapping("/info/{gid}")
    public R<GroupPackageVo> queryInfo(@PathVariable("gid") Integer gid) {

        return R.ok(groupPackageService.queryInfo(gid));
    }

    @ApiOperation(value = "添加产品组合套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "组合套餐名称"),
            @ApiImplicitParam(name = "cover", value = "组合套餐图片"),
            @ApiImplicitParam(name = "price", value = "套餐价格"),
            @ApiImplicitParam(name = "freightType", value = "运费类型 运费：0.包邮1.按默认设置 自定义"),
            @ApiImplicitParam(name = "freight", value = "运费价格"),
            @ApiImplicitParam(name = "discount", value = "自定义折扣 0.默认折扣"),
            @ApiImplicitParam(name = "groupProductList;", value = "groupProductList, 参数：pid,sku_id(可空) num"),
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody GroupPackage groupPackage) {

        return R.ok(groupPackageService.insert(groupPackage));
    }

    @ApiOperation(value = "修改产品组合套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gid", value = "产品组合套餐id", required = true),
            @ApiImplicitParam(name = "title", value = "组合套餐名称", required = true),
            @ApiImplicitParam(name = "cover", value = "组合套餐图片"),
            @ApiImplicitParam(name = "price", value = "套餐价格"),
            @ApiImplicitParam(name = "freightType", value = "运费类型 运费：0.包邮1.按默认设置 自定义"),
            @ApiImplicitParam(name = "freight", value = "运费价格"),
            @ApiImplicitParam(name = "discount", value = "自定义折扣 0.默认折扣"),
            @ApiImplicitParam(name = "groupProductList;", value = "groupProductList, 参数：pid,sku_id(可空) num")
    })
    @PutMapping("/update")
    public R<Boolean> updateInfo(@RequestBody GroupPackage groupPackage) {

        return R.ok(groupPackageService.updateInfo(groupPackage));
    }

    @ApiOperation(value = "删除产品组合套餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> gids) {

        return R.ok(groupPackageService.removeByIds(gids));
    }

}
