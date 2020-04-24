package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.PermissionRoleService;
import com.zhancheng.core.dao.PermissionRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.PermissionRole;

import javax.annotation.Resource;


/**
 * 后台功能权限分配
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Api(tags = "后台功能权限分配")
@RestController
@RequestMapping("/permissionRoles")
public class PermissionRoleController {

    @Resource
    private PermissionRoleService permissionRoleService;

    @Resource
    private PermissionRoleMapper permissionRoleMapper;

    @ApiOperation(value = "分页查询后台功能权限分配列表")
    @GetMapping("/list")
    public R<List<PermissionRole>> queryPage(Integer rid){

        return R.ok(permissionRoleMapper.queryList(rid));
    }


    @ApiOperation(value = "添加后台功能权限分配")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "rid", value = "角色id", required = true),
                        @ApiImplicitParam(name = "pid", value = "权限id", required = true)
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody PermissionRole permissionRole){

        return R.ok(permissionRoleService.save(permissionRole));
    }


    @ApiOperation(value = "删除后台功能权限分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(permissionRoleService.removeByIds(ids));
    }

}
