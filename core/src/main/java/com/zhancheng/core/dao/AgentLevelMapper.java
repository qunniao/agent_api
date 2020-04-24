package com.zhancheng.core.dao;

import com.zhancheng.core.entity.AgentLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.AgentLevelListVo;
import com.zhancheng.core.vo.AgentListVo;
import org.apache.ibatis.annotations.Mapper;
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
public interface AgentLevelMapper extends BaseMapper<AgentLevel> {

    List<AgentLevelListVo> queryList();
}
