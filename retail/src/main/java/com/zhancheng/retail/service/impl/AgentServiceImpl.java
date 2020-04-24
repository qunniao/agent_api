package com.zhancheng.retail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.retail.service.AgentService;
import org.springframework.stereotype.Service;

/**
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */

@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements AgentService {

    @Override
    public Agent queryInfo(Integer id) {
        return baseMapper.queryInfo(id);
    }
}