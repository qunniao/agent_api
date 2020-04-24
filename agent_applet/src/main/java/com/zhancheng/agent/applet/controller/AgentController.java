package com.zhancheng.agent.applet.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.AgentService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentDto;
import com.zhancheng.core.dto.FranchiseeDto;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.entity.Agent;

import javax.annotation.Resource;


/**
 * 
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
@Api(tags = "代理")
@RestController
@RequestMapping("/agents")
public class AgentController {

    @Resource
    private AgentService agentService;

    @ApiOperation(value = "查询详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Agent> info(@PathVariable("id") Integer id){

        return R.ok(agentService.queryInfo(id));
    }

    @ApiOperation(value = "注册代理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trueName", value = "真实姓名", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "levelId", value = "等级id", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "inviter", value = "邀请人id：0是总部", required = true),
            @ApiImplicitParam(name = "superior", value = "上级id：0是总部", required = true),
            @ApiImplicitParam(name = "code", value = "微信授权code", required = true),
            @ApiImplicitParam(name = "agentCartList", value = "代理购物车集合"),
    })
    @PostMapping("/register")
    public R<Agent> register(@RequestBody AgentDto agentDto) throws Exception {

        return R.ok(agentService.register(agentDto));
    }

    @ApiOperation(value = "查询手机号是否重复", notes = "重复为true, 不重复为false")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true)
    })
    @GetMapping("/queryPhone")
    public R<Boolean> queryPhone(String phone){

        Agent agent = agentService.getOne(new QueryWrapper<Agent>()
                .eq("phone", phone));

        return R.ok(ObjectUtil.isNotNull(agent));
    }

    @ApiOperation(value = "加盟店注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trueName", value = "真实姓名", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "inviter", value = "邀请人id：0是总部", required = true),
            @ApiImplicitParam(name = "code", value = "微信授权code", required = true),
            @ApiImplicitParam(name = "adminPhone", value = "管理员手机号", required = true),
            @ApiImplicitParam(name = "storeName", value = "店铺名称", required = true),
            @ApiImplicitParam(name = "region", value = "地址", required = true),
    })
    @PostMapping("/register/franchisee")
    public R<Agent> register(@RequestBody FranchiseeDto franchiseeDto) {

        return R.ok(agentService.registerFranchisee(franchiseeDto));
    }

    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true),
    })
    @PostMapping("/update/password")
    public R<Boolean> register(String phone, String password, String code) {

        return R.ok(agentService.updatePassword(phone, password, code));
    }
}
