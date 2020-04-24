package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.PayeeAccountService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.PayeeAccount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "查询总部账户列表")
    @GetMapping("/list")
    public R<List<PayeeAccount>> list(){

        return R.ok(payeeAccountService.queryList());
    }

    @ApiOperation(value = "查询总部账户详情")
    @ApiImplicitParam(name = "id", value = "账户id")
    @GetMapping("/info/{id}")
    public R<PayeeAccount> info(@PathVariable Integer id){

        return R.ok(payeeAccountService.getById(id));
    }

    @ApiOperation(value = "修改总部账户")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "总部账户id", required = true),
            @ApiImplicitParam(value = "type", name = "账户类别1.银行卡2.支付宝3.微信", required = true),
            @ApiImplicitParam(value = "trueName", name = "姓名", required = true),
            @ApiImplicitParam(value = "account", name = "账户", required = true),
            @ApiImplicitParam(value = "depositBank", name = "开户行信息", required = true),
            @ApiImplicitParam(value = "qrCode", name = "收款二维码", required = true)
    })
    public R<Boolean> update(PayeeAccount payeeAccount){

        return R.ok(payeeAccount.updateById());
    }

}
