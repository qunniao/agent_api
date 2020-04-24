package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.MenuRoleService;
import com.zhancheng.core.dao.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.MenuRole;

import javax.annotation.Resource;


/**
 * 后台菜单权限
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Api(tags = "后台菜单权限")
@RestController
@RequestMapping("/menuRoles")
public class MenuRoleController {

    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Resource
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "分页查询后台菜单权限列表")
    @GetMapping("/list")
    public R<List<MenuRole>> queryList(Integer rid){

        return R.ok(menuRoleMapper.queryList(rid));
    }

    @ApiOperation(value = "添加后台菜单权限")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "rid", value = "角色id", required = true),
                        @ApiImplicitParam(name = "mid", value = "菜单id", required = true),
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody MenuRole menuRole){

        return R.ok(menuRoleService.save(menuRole));
    }

    @ApiOperation(value = "删除后台菜单权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(menuRoleService.removeByIds(ids));
    }

}
