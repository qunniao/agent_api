package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentTeamDto;
import com.zhancheng.core.vo.AgentTeamListVo;

import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentTeamService
 * @date 2019/11/1 17:15
 */
public interface AgentTeamService {

  IPage<AgentTeamListVo> queryPage(PageDto pageDto, AgentTeamDto agentTeamDto);

  List<AgentTeamListVo> queryList(AgentTeamDto agentTeamDto);

  AgentTeamListVo querySuperior(AgentTeamDto agentTeamDto);
}
