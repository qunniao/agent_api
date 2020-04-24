package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.AgentRegisterReviewService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.vo.AgentReviewListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代理加入审核
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */
@Api(tags = "代理加入审核")
@RestController
@RequestMapping("/agentRegisterReviews")
public class AgentRegisterReviewController {

    @Resource
    private AgentRegisterReviewService agentRegisterReviewService;

    @ApiOperation(value = "分页查询代理加入审核列表")
    @GetMapping("/list")
    public R<IPage<AgentReviewListVo>> queryPage(PageDto<AgentRegisterReview> pageDto, AgentReviewsQueryDto agentReviewsQueryDto ){

        return R.ok(agentRegisterReviewService.queryPage(pageDto, agentReviewsQueryDto));
    }
}
