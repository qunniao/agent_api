package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.vo.AgentLevelListVo;
import com.zhancheng.core.vo.AgentListVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */

@Service
public class AgentLevelServiceImpl extends ServiceImpl<AgentLevelMapper, AgentLevel> implements AgentLevelService {


    @Override
    public List<AgentLevelListVo> queryList() {
        return baseMapper.queryList();
    }
}