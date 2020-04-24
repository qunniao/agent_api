package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.WxPayService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.TransferDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription 支付接口
 * @project WxPayController
 * @date 2019/11/6 21:03
 */
@Api(tags = "支付接口")
@RestController
@RequestMapping("/pay")
public class WxPayController {

    @Resource
    private WxPayService wxPayService;

    Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @ApiOperation(value = "余额付款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "agentId", value = "代理id")
    })
    @PostMapping("/balancePay")
    public R<Boolean> balancePay(Integer orderId, Integer agentId) {

        return R.ok(wxPayService.balancePay(orderId, agentId));
    }

    @ApiOperation(value = "微信支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "agentId", value = "代理id")
    })

    @PostMapping("/agentWxPay")
    public R<Map<String, String>> agentWxPay(Integer orderId, Integer agentId) throws Exception {

        return R.ok(wxPayService.agentWxPay(orderId, agentId));
    }

    /**
     * 回调接口
     * @param notifyXml
     */
    @PostMapping("/wxPayUnifiedNotify")
    public void wxPayUnifiedNotify(@RequestBody String notifyXml, HttpServletResponse response) {
        String result = wxPayService.wxPayUnifiedNotify(notifyXml);

        // 判断是否支付成功
        if (result.contains("FAIL")) {
            logger.error(result);
        }
        logger.info(result);
        writeResp(response, result);
    }

    private void writeResp(HttpServletResponse response, String result) {
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
