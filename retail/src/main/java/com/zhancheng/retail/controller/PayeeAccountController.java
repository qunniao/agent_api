package com.zhancheng.retail.controller;

import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.PayeeAccount;
import com.zhancheng.retail.service.PayeeAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation(value = "查询总部账户")
    @GetMapping("/list")
    public R<List<PayeeAccount>> list(Integer agentId){

        return R.ok(payeeAccountService.queryList(agentId));
    }

}
