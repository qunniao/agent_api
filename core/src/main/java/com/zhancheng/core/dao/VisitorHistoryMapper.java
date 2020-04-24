package com.zhancheng.core.dao;

import com.zhancheng.core.entity.VisitorHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 访客访问记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */
@Repository
public interface VisitorHistoryMapper extends BaseMapper<VisitorHistory> {
	
}
