package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.vo.AgentLevelListVo;
import com.zhancheng.core.vo.AgentListVo;

import java.util.List;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */
public interface AgentLevelService extends IService<AgentLevel> {

    List<AgentLevelListVo> queryList();
}

