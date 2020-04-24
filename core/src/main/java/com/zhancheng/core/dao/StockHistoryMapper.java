package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.StockHistoryQueryDto;
import com.zhancheng.core.entity.StockHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.StockHistoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 库存历史
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-22 14:13:44
 */
@Repository
public interface StockHistoryMapper extends BaseMapper<StockHistory> {

    /**
     * 分页查询库存记录信息
     * @param page 分页信息
     * @param stockHistoryQueryDto 查询数据
     * @return IPage<StockHistoryVo>
     */
    IPage<StockHistoryVo> queryPage(Page page, @Param("query") StockHistoryQueryDto stockHistoryQueryDto);
	
}
