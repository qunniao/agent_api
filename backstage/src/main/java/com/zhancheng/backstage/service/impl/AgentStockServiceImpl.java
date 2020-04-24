package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.AgentStockService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AgentStockMapper;
import com.zhancheng.core.entity.AgentStock;

/**
 * 代理库存
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 16:02:47
 */

@Service
public class AgentStockServiceImpl extends ServiceImpl<AgentStockMapper, AgentStock> implements AgentStockService {

}