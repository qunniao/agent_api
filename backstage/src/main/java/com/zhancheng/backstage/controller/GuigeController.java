package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.GuigeService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Guige;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-19 14:47:52
 */
@Api(tags = "规格")
@RestController
@RequestMapping("/guiges")
public class GuigeController {

    @Resource
    private GuigeService guigeService;

    @ApiOperation(value = "查询规格列表")
    @GetMapping("/list")
    public R<List<Guige>> list(){

        return R.ok(guigeService.selectList());
    }

    @ApiOperation(value = "查询规格详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sgid", value = "主键id",required = true),
    })
    @GetMapping("/info")
    public R<Guige> info(Integer sgid){

        return R.ok(guigeService.info(sgid));
    }

    @ApiOperation(value = "添加规格")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "title", value = "规格名称",required = true),
                        @ApiImplicitParam(name = "sort", value = "排序", required = true)
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Guige guige){

        return R.ok(guigeService.insert(guige));
    }

    @ApiOperation(value = "修改规格参数")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "sgid", value = "规格id", required = true),
                        @ApiImplicitParam(name = "skus", value = "多个sku"),
            })
    @PutMapping("/updateSku")

    public R<Boolean> update(@RequestBody Guige guige){
        return R.ok(guigeService.update(guige));
    }
    @ApiOperation(value = "批量修改规格排序")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "sgid", value = "规格id",required = true),
                        @ApiImplicitParam(name = "sort", value = "排序"),
            })
    @PutMapping("/updateSort")
    public R<Boolean> update(@RequestBody List<Guige> guige){
        return R.ok( guigeService.updateBatchById(guige));
    }

    @ApiOperation(value = "删除规格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sgids", value = "主键id",required = true),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> sgids){

        return R.ok(guigeService.delete(sgids));
    }

}
