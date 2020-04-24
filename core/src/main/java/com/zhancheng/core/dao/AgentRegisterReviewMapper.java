package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.AgentReviewListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 代理加入审核
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */
@Repository
public interface AgentRegisterReviewMapper extends BaseMapper<AgentRegisterReview> {

    IPage<AgentReviewListVo> queryPage(Page page, @Param("query") AgentReviewsQueryDto agentReviewsQueryDto);

}
