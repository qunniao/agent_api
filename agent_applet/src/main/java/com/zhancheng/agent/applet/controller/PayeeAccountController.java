package com.zhancheng.agent.applet.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.PayeeAccountService;
import com.zhancheng.core.constant.R;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.PayeeAccount;


import javax.annotation.Resource;


/**
 * 总部账户
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 18:02:48
 */
@Api(tags = "总部账户")
@RestController
@RequestMapping("/payeeAccounts")
public class PayeeAccountController {

    @Resource
    private PayeeAccountService payeeAccountService;

    @ApiOperation(value = "查询账户")
    @GetMapping("/list")
    public R<List<PayeeAccount>> list(Integer agentId){

        return R.ok(payeeAccountService.queryList(agentId));
    }

}
