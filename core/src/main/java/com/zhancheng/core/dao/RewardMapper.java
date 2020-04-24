package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.RewardQueryDto;
import com.zhancheng.core.dto.StockHistoryQueryDto;
import com.zhancheng.core.entity.Reward;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.RewardVo;
import com.zhancheng.core.vo.StockHistoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 奖励记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-13 16:16:08
 */
@Repository
public interface RewardMapper extends BaseMapper<Reward> {

    IPage<RewardVo> queryPage(Page page, @Param("query") RewardQueryDto rewardQueryDto);
}
