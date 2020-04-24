package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.AgentHistory;

/**
 * 代理变更记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 10:47:24
 */
public interface AgentHistoryService extends IService<AgentHistory> {

    /**
     * 查询代理注册时的信息
     * @param agentId
     * @return
     */
    AgentHistory queryInfo(Integer agentId);
}

