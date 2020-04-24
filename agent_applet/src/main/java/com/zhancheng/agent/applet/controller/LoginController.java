package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.LoginService;
import com.zhancheng.core.constant.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginController
 * @date 2019/10/26 11:48
 */
@Api(tags = "")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录", notes = "传入从微信获得code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "凭证", required = true)
    })
    public R<Map<String, Object>> wxLogin(String code) throws Exception {

        return R.ok(loginService.wxLogin(code));
    }

//    @PostMapping("/accountLogin")
//    @ApiOperation(value = "手机号密码登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
//            @ApiImplicitParam(name = "password", value = "密码", required = true),
//            @ApiImplicitParam(name ="code", value = "传入从微信获得code")
//    })
//    public R<Map<String, Object>> accountLogin(String phone, String password, String code){
//
//        return R.ok(loginService.accountLogin(phone, password, code));
//    }

    @PostMapping("/weChat/untie")
    @ApiOperation(value = "微信解绑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id", required = true)
    })
    public R<Boolean> weChatUntie(Integer agentId){

        return R.ok(loginService.weChatUntie(agentId));
    }

    @PostMapping("/account")
    @ApiOperation(value = "手机号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name ="code", value = "手机验证码")
    })
    public R<Map<String, Object>> phoneLogin(String phone, String password, String code){

       return R.ok(loginService.phoneLogin(phone, password, code));
    }
}
