package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.AgentStockProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentStockQueryDto;
import com.zhancheng.core.vo.AgentStockProductVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AgentStockProductMapper;
import com.zhancheng.core.entity.AgentStockProduct;

/**
 * 代理库存商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-02 11:47:42
 */

@Service
public class AgentStockProductServiceImpl extends ServiceImpl<AgentStockProductMapper, AgentStockProduct> implements AgentStockProductService {

    @Override
    public IPage<AgentStockProductVo> queryPage(PageDto<AgentStockProduct> pageDto, AgentStockQueryDto agentStockQueryDto) {

        return baseMapper.queryAppletPage(pageDto.getPage(), agentStockQueryDto);
    }
}