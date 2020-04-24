package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.AgentTeamService;
import com.zhancheng.agent.applet.service.impl.AgentTeamServiceImpl;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentTeamDto;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.vo.AgentTeamListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentTeamController
 * @date 2019/11/1 16:55
 */
@Api(tags = "代理团队")
@RestController
@RequestMapping("/agentTeam")
public class AgentTeamController {

    @Resource
    private AgentTeamService agentTeamService;

    @ApiOperation(value = "分页查询代理团队")
    @GetMapping("/page")
    public R<IPage<AgentTeamListVo>> queryPage(PageDto pageDto, AgentTeamDto agentTeamDto){

        return R.ok(agentTeamService.queryPage(pageDto, agentTeamDto));
    }

    @ApiOperation(value = "查询代理团队")
    @GetMapping("/list")
    public R<List<AgentTeamListVo>> queryList(AgentTeamDto agentTeamDto){

        return R.ok(agentTeamService.queryList(agentTeamDto));
    }

    @ApiOperation(value = "查询代理上级")
    @GetMapping("/querySuperior")
    public R<AgentTeamListVo> querySuperior(AgentTeamDto agentTeamDto){

        return R.ok(agentTeamService.querySuperior(agentTeamDto));
    }
}
