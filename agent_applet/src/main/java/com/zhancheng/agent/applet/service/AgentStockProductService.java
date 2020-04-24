package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentStockQueryDto;
import com.zhancheng.core.entity.AgentStockProduct;
import com.zhancheng.core.vo.AgentStockProductVo;

/**
 * 代理库存商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-02 11:47:42
 */
public interface AgentStockProductService extends IService<AgentStockProduct> {

   IPage<AgentStockProductVo> queryPage(PageDto<AgentStockProduct> pageDto, AgentStockQueryDto agentStockQueryDto);
}

