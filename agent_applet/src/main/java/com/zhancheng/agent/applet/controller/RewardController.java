package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.RewardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.RewardQueryDto;
import com.zhancheng.core.entity.Reward;
import com.zhancheng.core.vo.RewardVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 奖励记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-13 16:16:08
 */
@Api(tags = "奖励记录")
@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Resource
    private RewardService rewardService;

    @ApiOperation(value = "分页查询奖励记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id")
    })
    @GetMapping("/page")
    public R<IPage<RewardVo>> queryPage(PageDto<Reward> pageDto, RewardQueryDto rewardQueryDto){

        return R.ok(rewardService.queryPage(pageDto, rewardQueryDto));
    }

}
