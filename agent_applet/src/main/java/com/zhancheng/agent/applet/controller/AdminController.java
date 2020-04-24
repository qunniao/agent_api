package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.AdminService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Api(tags = "管理员")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation(value = "查询加盟店管理员列表")
    @GetMapping("/franchisee/list")
    public R<List<Admin>> list(){

        return R.ok(adminService.list(new QueryWrapper<Admin>().eq("role_id", 3)));
    }

}
