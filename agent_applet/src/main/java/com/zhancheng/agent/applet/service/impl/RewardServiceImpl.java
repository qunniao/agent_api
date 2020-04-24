package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.RewardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.RewardMapper;
import com.zhancheng.core.dto.RewardQueryDto;
import com.zhancheng.core.entity.Reward;
import com.zhancheng.core.vo.RewardVo;
import org.springframework.stereotype.Service;

/**
 * 奖励记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-13 16:16:08
 */

@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements RewardService {

    @Override
    public IPage<RewardVo> queryPage(PageDto<Reward> pageDto, RewardQueryDto rewardQueryDto) {
        return baseMapper.queryPage(pageDto.getPage(), rewardQueryDto);
    }
}