package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.AgentDto;
import com.zhancheng.core.entity.Agent;


/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
public interface AgentService extends IService<Agent> {

    Agent queryInfo(Integer id);


}

