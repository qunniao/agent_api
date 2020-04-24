package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.AgentWalletService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentWalletDTO;
import com.zhancheng.core.dto.FranchiseeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentWallet
 * @date 2019/11/28 14:56
 */
@Api(tags = "代理钱包")
@RestController
@RequestMapping("/wallet")
public class AgentWalletController {

    @Resource
    private AgentWalletService agentWalletService;


    @ApiOperation(value = "调整余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id", required = true),
            @ApiImplicitParam(name = "adminId", value = "操作人id", required = true),
            @ApiImplicitParam(name = "changeType", value = "调整类型1：增加，2：减少", required = true),
            @ApiImplicitParam(name = "amount", value = "金额", required = true),
            @ApiImplicitParam(name = "tradeDesc", value = "交易描述信息"),
    })
    @PostMapping("/adjust")
    public R<Boolean> adjust(@RequestBody AgentWalletDTO agentWalletDTO) {

        return R.ok(agentWalletService.adjust(agentWalletDTO));
    }
}
