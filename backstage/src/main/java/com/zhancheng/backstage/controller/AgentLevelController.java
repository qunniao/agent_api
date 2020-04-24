package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.vo.AgentLevelListVo;
import com.zhancheng.core.vo.AgentListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public R<List<AgentLevelListVo>> queryList(){

        return R.ok(agentLevelService.queryList());
    }

    @ApiOperation(value = "查询等级详情")
    @GetMapping("/info/{id}")
    public R<AgentLevel> queryList(@PathVariable Integer id){

        return R.ok(agentLevelService.getById(id));
    }

    @ApiOperation(value = "编辑等级")
    @PostMapping("/update")
    public R<Boolean> queryList(AgentLevel agentLevel){

        return R.ok(agentLevel.updateById());
    }

}
