package com.zhancheng.core.dao;

import com.zhancheng.core.entity.AgentHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 代理变更记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 10:47:24
 */
@Repository
public interface AgentHistoryMapper extends BaseMapper<AgentHistory> {
	
}
