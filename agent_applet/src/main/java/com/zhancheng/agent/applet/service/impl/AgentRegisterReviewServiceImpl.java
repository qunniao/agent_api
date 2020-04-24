package com.zhancheng.agent.applet.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.AgentRegisterReviewService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.AgentRegisterReviewMapper;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.vo.AgentReviewListVo;
import org.springframework.stereotype.Service;

/**
 * 代理加入审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */

@Service
public class AgentRegisterReviewServiceImpl extends ServiceImpl<AgentRegisterReviewMapper, AgentRegisterReview> implements AgentRegisterReviewService {

    @Override
    public IPage<AgentReviewListVo> queryPage(PageDto<AgentRegisterReview> pageDto, AgentReviewsQueryDto agentReviewsQueryDto) {
        return baseMapper.queryPage(pageDto.getPage(), agentReviewsQueryDto);
    }

}