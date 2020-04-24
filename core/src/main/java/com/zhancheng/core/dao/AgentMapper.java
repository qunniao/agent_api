package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.AgentQueryDto;
import com.zhancheng.core.dto.AgentTeamDto;
import com.zhancheng.core.entity.Agent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.AgentInfoVo;
import com.zhancheng.core.vo.AgentListVo;
import com.zhancheng.core.vo.AgentTeamListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
@Repository
public interface AgentMapper extends BaseMapper<Agent> {

    IPage<AgentListVo> queryPage(Page page, @Param("query") AgentQueryDto agentQueryDto);

    IPage<AgentInfoVo> queryInfoList(Page page, @Param("query") AgentQueryDto agentQueryDto);

    Agent queryInfo(Integer id);

    Agent queryById(Integer id);

    /**
     * 根据代理id查询代理等级
     * @param agentId 代理id
     * @return
     */
    String queryAgentLevel(@Param("agentId") Integer agentId);
    /**
     * 根据代理id分页查询代理团队
     * @param page 分页数据
     * @param agentTeamDto 查询参数
     * @return
     */
    IPage<AgentTeamListVo> queryTeamPage(Page page, @Param("query") AgentTeamDto agentTeamDto);

    /**
     * 根据代理id分页查询代理团队
     * @param agentTeamDto 查询参数
     * @return
     */
    List<AgentTeamListVo> queryTeamList(@Param("query") AgentTeamDto agentTeamDto);

    /**
     * 根据代理id分页查询代理团队
     * @param agentTeamDto 查询参数
     * @return
     */
    AgentTeamListVo querySuperior(@Param("query") AgentTeamDto agentTeamDto);

}
