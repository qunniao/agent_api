package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.vo.AgentReviewListVo;

/**
 * 代理加入审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */
public interface AgentRegisterReviewService extends IService<AgentRegisterReview> {

   IPage<AgentReviewListVo> queryPage(PageDto<AgentRegisterReview> pageDto, AgentReviewsQueryDto agentReviewsQueryDto);

}

