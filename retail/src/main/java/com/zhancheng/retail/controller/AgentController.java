package com.zhancheng.retail.controller;

import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.retail.service.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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



}
