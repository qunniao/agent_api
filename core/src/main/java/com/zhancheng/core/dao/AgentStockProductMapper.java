package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentStockQueryDto;
import com.zhancheng.core.entity.AgentStockProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.AgentStockProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 代理库存商品
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-02 11:47:42
 */
@Repository
public interface AgentStockProductMapper extends BaseMapper<AgentStockProduct> {

    IPage<AgentStockProductVo> queryAppletPage(Page page, @Param("query") AgentStockQueryDto agentStockQueryDto);
}
