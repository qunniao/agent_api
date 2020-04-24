package com.zhancheng.core.dao;

import com.zhancheng.core.entity.AgentStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 代理库存
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 16:02:47
 */
@Repository
public interface AgentStockMapper extends BaseMapper<AgentStock> {
	
}
