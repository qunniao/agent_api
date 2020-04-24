package com.zhancheng.agent.applet.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.AgentLevelService;
import com.zhancheng.core.constant.R;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.zhancheng.core.entity.AgentLevel;

import javax.annotation.Resource;

/**
 * 
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
@Api(tags = "代理等级")
@RestController
@RequestMapping("/agentLevels")
public class AgentLevelController {

    @Resource
    private AgentLevelService agentLevelService;

    @ApiOperation(value = "查询等级列表")
    @GetMapping("/list")
    public R<List<AgentLevel>> list(){

        return R.ok(agentLevelService.list(new QueryWrapper<AgentLevel>().eq("is_dark_level", 0)));
    }

}
