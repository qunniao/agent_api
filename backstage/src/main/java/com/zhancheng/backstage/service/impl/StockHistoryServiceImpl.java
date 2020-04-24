package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.StockHistoryService;
import com.zhancheng.core.dto.StockHistoryQueryDto;
import com.zhancheng.core.vo.StockHistoryVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.dao.StockHistoryMapper;
import com.zhancheng.core.entity.StockHistory;

import java.util.List;

/**
 * 库存历史
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-22 14:13:44
 */

@Service
public class StockHistoryServiceImpl extends ServiceImpl<StockHistoryMapper, StockHistory> implements StockHistoryService {

    @Override
    public IPage<StockHistoryVo> selectPage(PageDto<StockHistory> pageDto, StockHistoryQueryDto stockHistoryQueryDto) {
        return baseMapper.queryPage(pageDto.getPage(), stockHistoryQueryDto);
    }

    @Override
    public StockHistory info(Integer id) {

        return baseMapper.selectById(id);
    }

    @Override
    public Boolean insert(StockHistory stockHistory) {
        return  baseMapper.insert(stockHistory) > 0;
    }

    @Override
    public Boolean update(StockHistory stockHistory) {
        return baseMapper.updateById(stockHistory) > 0;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}