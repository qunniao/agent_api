package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentRegisterReviewService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.vo.AgentReviewListVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.commom.PageDto;

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

    @ApiOperation(value = "通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true),
    })
    @PostMapping("/ratifyVoucher")
    public R<Boolean> ratifyVoucher(Integer id){

        return R.ok(agentRegisterReviewService.ratifyVoucher(id));
    }
    @ApiOperation(value = "驳回审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true),
            @ApiImplicitParam(name = "refuseReason", value = "驳回原因,驳回时传参"),
    })
    @PostMapping("/rejectVoucher")
    public R<Boolean> rejectVoucher(Integer id, String refuseReason){

        return R.ok(agentRegisterReviewService.rejectVoucher(id, refuseReason));
    }

}
