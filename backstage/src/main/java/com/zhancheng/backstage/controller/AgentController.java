package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentQueryDto;
import com.zhancheng.core.dto.FranchiseeDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.vo.AgentInfoVo;
import com.zhancheng.core.vo.AgentListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


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

    @ApiOperation(value = "查询代理人列表")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/list")
    public R<IPage<AgentListVo>> queryPage(PageDto pageDto, AgentQueryDto agentQueryDto){

        return R.ok(agentService.queryPage(pageDto, agentQueryDto));
    }

    @ApiOperation(value = "查询加盟店列表")
    @ApiImplicitParam(name = "levelId", value = "代理等级")
    @GetMapping("/infoList")
    public R<IPage<AgentInfoVo>> queryInfoList(PageDto pageDto, AgentQueryDto agentQueryDto){

        return R.ok(agentService.queryInfoList(pageDto, agentQueryDto));
    }

    @ApiOperation(value = "查询代理人详细信息")
    @ApiImplicitParam(name = "id", value = "代理id")
    @GetMapping("/info/{id}")
    public R<Agent> queryInfo(@PathVariable("id") Integer id){

        return R.ok(agentService.queryInfo(id));
    }

    @ApiOperation(value = "删除代理")
    @ApiImplicitParam(name = "id", value = "代理id")
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") Integer id){

        return R.ok(agentService.removeById(id));
    }

    @ApiOperation(value = "加盟店注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trueName", value = "真实姓名", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "adminId", value = "管理员id", required = true),
            @ApiImplicitParam(name = "storeName", value = "店铺名称", required = true),
            @ApiImplicitParam(name = "region", value = "地址", required = true),
    })
    @PostMapping("/register/franchisee")
    public R<Boolean> register(@RequestBody FranchiseeDto franchiseeDto) {

        return R.ok(agentService.registerFranchisee(franchiseeDto));
    }

    @ApiOperation(value = "加盟店修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "代理id", required = true),
            @ApiImplicitParam(name = "trueName", value = "真实姓名"),
            @ApiImplicitParam(name = "cover", value = "头像"),
            @ApiImplicitParam(name = "adminId", value = "管理员id"),
            @ApiImplicitParam(name = "inviterPhone", value = "邀请人手机号"),
            @ApiImplicitParam(name = "superior", value = "进货上级id"),
            @ApiImplicitParam(name = "idCard", value = "身份证号"),
            @ApiImplicitParam(name = "birthday", value = "出生日期"),
            @ApiImplicitParam(name = "education", value = "学历"),
            @ApiImplicitParam(name = "region", value = "地址"),
            @ApiImplicitParam(name = "gender", value = "性别：1.男0.女"),
    })
    @PostMapping("/update/franchisee")
    public R<Boolean> update(@RequestBody Agent agent) {

        return R.ok(agentService.updateFranchisee(agent));
    }

}
