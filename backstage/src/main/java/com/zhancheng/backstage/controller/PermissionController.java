package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Permission;

import javax.annotation.Resource;


/**
 * 后台功能权限
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Api(tags = "后台功能权限")
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "分页查询后台功能权限列表")
    @GetMapping("/page")
    public R<IPage<Permission>> queryPage(PageDto<Permission> pageDto){

        return R.ok(permissionService.page(pageDto.getPage()));
    }

    @ApiOperation(value = "查询后台功能权限详情")
    @ApiImplicitParam(name = "pid", value = "主键id")
    @GetMapping("/info/{pid}")
    public R<Permission> queryInfo(@PathVariable("pid") Integer pid){

        return R.ok(permissionService.getById(pid));
    }

    @ApiOperation(value = "添加后台功能权限")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "mid", value = "菜单id"),
                        @ApiImplicitParam(name = "controller", value = "控制器名"),
                        @ApiImplicitParam(name = "method", value = "方法名"),
                        @ApiImplicitParam(name = "remark", value = "权限描述"),
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Permission permission){

        return R.ok(permissionService.save(permission));
    }

    @ApiOperation(value = "修改后台功能权限")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "pid", value = "功能权限id", required = true),
                        @ApiImplicitParam(name = "mid", value = "菜单id"),
                        @ApiImplicitParam(name = "controller", value = "控制器名", required = true),
                        @ApiImplicitParam(name = "method", value = "方法名", required = true),
                        @ApiImplicitParam(name = "remark", value = "权限描述"),
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Permission permission){

        return R.ok(permission.updateById());
    }

    @ApiOperation(value = "删除后台功能权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> pids){

        return R.ok(permissionService.removeByIds(pids));
    }

}
