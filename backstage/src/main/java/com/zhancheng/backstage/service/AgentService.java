package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentQueryDto;
import com.zhancheng.core.dto.FranchiseeDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.vo.AgentInfoVo;
import com.zhancheng.core.vo.AgentListVo;



/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
public interface AgentService extends IService<Agent> {

    IPage<AgentListVo> queryPage(PageDto pageDto, AgentQueryDto agentQueryDto);

    IPage<AgentInfoVo> queryInfoList(PageDto pageDto, AgentQueryDto agentQueryDto);

    Agent queryInfo(Integer id);

    Boolean registerFranchisee(FranchiseeDto franchiseeDto);

    Boolean updateFranchisee(Agent agent);
}

