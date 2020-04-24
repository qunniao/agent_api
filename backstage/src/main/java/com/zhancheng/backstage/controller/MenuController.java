package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Menu;

import javax.annotation.Resource;


/**
 * 后台菜单
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Api(tags = "后台菜单")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "分页查询后台菜单列表")
    @GetMapping("/page")
    public R<IPage<Menu>> queryPage(PageDto<Menu> pageDto){

        return R.ok(menuService.page(pageDto.getPage()));
    }

    @ApiOperation(value = "查询后台菜单详情")
    @ApiImplicitParam(name = "mid", value = "主键id")
    @GetMapping("/info/{mid}")
    public R<Menu> queryInfo(@PathVariable("mid") Integer mid){

        return R.ok(menuService.getById(mid));
    }

    @ApiOperation(value = "添加后台菜单")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "fid", value = "父级id"),
                        @ApiImplicitParam(name = "name", value = "菜单名", required = true),
                        @ApiImplicitParam(name = "icon", value = "图标"),
                        @ApiImplicitParam(name = "url", value = "路径", required = true),
                        @ApiImplicitParam(name = "sort", value = "排序"),
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Menu menu){

        return R.ok(menuService.save(menu));
    }

    @ApiOperation(value = "修改后台菜单")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "mid", value = "菜单id", required = true),
                        @ApiImplicitParam(name = "fid", value = "父级id"),
                        @ApiImplicitParam(name = "name", value = "菜单名", required = true),
                        @ApiImplicitParam(name = "icon", value = "图标"),
                        @ApiImplicitParam(name = "url", value = "路径", required = true),
                        @ApiImplicitParam(name = "sort", value = "排序"),
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Menu menu){

        return R.ok(menu.updateById());
    }

    @ApiOperation(value = "删除后台菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> mids){

        return R.ok(menuService.removeByIds(mids));
    }

}
