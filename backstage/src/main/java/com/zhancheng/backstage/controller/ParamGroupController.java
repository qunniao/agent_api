package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ParamGroupService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.ParamGroup;
import com.zhancheng.core.vo.ParamGroupListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 产品属性组
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 17:06:38
 */
@Api(tags = "产品属性组")
@RestController
@RequestMapping("/paramGroups")
public class ParamGroupController {

    @Resource
    private ParamGroupService paramGroupService;

    @ApiOperation(value = "分页查询产品属性组列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "关键字"),
            @ApiImplicitParam(name = "sortType", value = "排序类型: 1；时间升序, 2：时间倒序")
    })
    @GetMapping("/page")
    public R<IPage<ParamGroupListVo>> page(PageDto<ParamGroup> pageDto, String keyWord, String sortType){

        return R.ok(paramGroupService.selectPage(pageDto, keyWord, sortType));
    }
    @ApiOperation(value = "查询产品属性组")
    @GetMapping("/list")
    public R<List<Map<String, String>>> list(){

        return R.ok(paramGroupService.selectList());
    }

    @ApiOperation(value = "查询产品属性组详情")
    @ApiImplicitParam(name = "pgid", value = "主键id")
    @GetMapping("/info/{pgid}")
    public R<ParamGroup> info(@PathVariable("pgid") Integer pgid){

        return R.ok(paramGroupService.info(pgid));
    }

    @ApiOperation(value = "添加产品属性组")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "title", value = "属性组名称",required = true),
                        @ApiImplicitParam(name = "names", value = "属性名",required = true)
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody ParamGroup paramGroup){

        return R.ok(paramGroupService.insert(paramGroup));
    }

    @ApiOperation(value = "修改产品属性组")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "pgid", value = "属性组id",required = true),
                        @ApiImplicitParam(name = "title", value = "属性组名称",required = true),
                        @ApiImplicitParam(name = "names", value = "属性名",required = true)
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody ParamGroup paramGroup){

        return R.ok(paramGroupService.update(paramGroup));
    }

    @ApiOperation(value = "删除产品属性组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pgids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> pgids){

        return R.ok(paramGroupService.delete(pgids));
    }

}
