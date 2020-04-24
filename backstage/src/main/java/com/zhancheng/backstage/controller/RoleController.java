package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.RoleService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Role;

import javax.annotation.Resource;


/**
 * 管理员角色
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Api(tags = "管理员角色")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "分页查询管理员角色列表")
    @GetMapping("/page")
    public R<IPage<Role>> queryPage(PageDto<Role> pageDto){

        return R.ok(roleService.page(pageDto.getPage()));
    }

    @ApiOperation(value = "查询管理员角色详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Role> queryInfo(@PathVariable("id") Integer id){

        return R.ok(roleService.getById(id));
    }

}
