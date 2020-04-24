package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.StockHistoryQueryDto;
import com.zhancheng.core.entity.StockHistory;
import com.zhancheng.core.vo.StockHistoryVo;

import java.util.List;

/**
 * 库存历史
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-22 14:13:44
 */
public interface StockHistoryService extends IService<StockHistory> {

    /**
     * 分页查询库存历史列表
     * @param pageDto 分页参数
     * @param stockHistoryQueryDto 查询参数
     * @return IPage<StockHistory>
     */
    IPage<StockHistoryVo> selectPage(PageDto<StockHistory> pageDto, StockHistoryQueryDto stockHistoryQueryDto);

    /**
     * 查询库存历史详情
     * @param id 主键Id
     * @return
     */
    StockHistory info(Integer id);

    /**
     * 添加库存历史
     * @param stockHistory 库存历史数据
     * @return Boolean
     */
    Boolean insert(StockHistory stockHistory);

    /**
     * 修改库存历史
     * @param stockHistory 库存历史数据
     * @return Boolean
     */
    Boolean update(StockHistory stockHistory);

    /**
     * 批量删除库存历史
     * @param ids 库存历史id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> ids);
}

