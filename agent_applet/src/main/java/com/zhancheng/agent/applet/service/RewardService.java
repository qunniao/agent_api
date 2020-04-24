package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.RewardQueryDto;
import com.zhancheng.core.entity.Reward;
import com.zhancheng.core.vo.RewardVo;

/**
 * 奖励记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-13 16:16:08
 */
public interface RewardService extends IService<Reward> {

   IPage<RewardVo> queryPage(PageDto<Reward> pageDto, RewardQueryDto rewardQueryDto);

}

