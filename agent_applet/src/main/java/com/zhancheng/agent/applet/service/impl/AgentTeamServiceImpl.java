package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.AgentTeamService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dto.AgentTeamDto;
import com.zhancheng.core.vo.AgentTeamListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentTeamServiceImpl
 * @date 2019/11/1 17:16
 */
@Service
public class AgentTeamServiceImpl implements AgentTeamService {

    @Resource
    private AgentMapper agentMapper;

    @Override
    public IPage<AgentTeamListVo> queryPage(PageDto pageDto, AgentTeamDto agentTeamDto) {

        return agentMapper.queryTeamPage(pageDto.getPage(), agentTeamDto);
    }

    @Override
    public List<AgentTeamListVo> queryList(AgentTeamDto agentTeamDto) {
        return agentMapper.queryTeamList(agentTeamDto);
    }

    @Override
    public AgentTeamListVo querySuperior(AgentTeamDto agentTeamDto) {

        System.err.println(agentTeamDto);

        return agentMapper.querySuperior(agentTeamDto);
    }
}
