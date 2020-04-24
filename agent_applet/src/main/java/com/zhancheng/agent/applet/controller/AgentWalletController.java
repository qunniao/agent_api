package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.AgentWalletMapper;
import com.zhancheng.core.entity.AgentWallet;
import com.zhancheng.core.entity.GroupPackage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentWalletController
 * @date 2019/11/9 17:31
 */
@Api(tags = "钱包")
@RestController
@RequestMapping("/wallet")
public class AgentWalletController {

    @Resource
    private AgentWalletMapper agentWalletMapper;

    @ApiOperation(value = "查询钱包信息")
    @GetMapping("/info/{agentId}")
    @ApiImplicitParam(name = "agentId", value = "代理id")
    public R<AgentWallet> queryList(@PathVariable Integer agentId) {

        return R.ok(agentWalletMapper.selectOne(new QueryWrapper<AgentWallet>().eq("agent_id", agentId)));
    }
}
