package com.zhancheng.core.dao;

import com.zhancheng.core.entity.AgentCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.OrderCartListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 购物车表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 18:05:12
 */
@Repository
public interface AgentCartMapper extends BaseMapper<AgentCart> {

    List<OrderCartListVo> queryList(Integer agentId);

    List<OrderCartListVo> queryInfo(@Param("cIds") List<Integer> cIds);
}
