package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.AgentHistoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhancheng.core.dao.AgentHistoryMapper;
import com.zhancheng.core.entity.AgentHistory;

/**
 * 代理变更记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 10:47:24
 */

@Service
public class AgentHistoryServiceImpl extends ServiceImpl<AgentHistoryMapper, AgentHistory> implements AgentHistoryService {

    @Override
    public AgentHistory queryInfo(Integer agentId) {
        return baseMapper.selectOne(new QueryWrapper<AgentHistory>().eq("agent_id", agentId).orderByAsc("gmt_create").last("LIMIT 1"));
    }
}