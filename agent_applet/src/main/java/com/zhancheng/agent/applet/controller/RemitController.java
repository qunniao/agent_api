package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.RemitService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Remit;
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
 * 打款审核
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */
@Api(tags = "打款审核")
@RestController
@RequestMapping("/remits")
public class RemitController {

    @Resource
    private RemitService remitService;

    @ApiOperation(value = "添加打款审核")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true),
                        @ApiImplicitParam(name = "amount", value = "打款金额", required = true),
                        @ApiImplicitParam(name = "remitterName", value = "打款人姓名"),
                        @ApiImplicitParam(name = "remitterPhone", value = "打款人电话"),
                        @ApiImplicitParam(name = "remitAccount", value = "打款账户"),
                        @ApiImplicitParam(name = "payeeAccount", value = "收款账户"),
                        @ApiImplicitParam(name = "paymentVoucher", value = "付款凭证（多张图片）", required = true),
                        @ApiImplicitParam(name = "remark", value = "备注信息"),
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Remit remit){

        return R.ok(remitService.insert(remit));
    }

}
