package com.zhancheng.core.dao;

import com.zhancheng.core.entity.AgentWallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 代理人钱包
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 13:53:37
 */
@Repository
public interface AgentWalletMapper extends BaseMapper<AgentWallet> {

    AgentWallet queryByAgentId(@Param("agentId") Integer agentId);
}
