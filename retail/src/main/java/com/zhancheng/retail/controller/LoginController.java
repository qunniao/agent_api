package com.zhancheng.retail.controller;

import com.zhancheng.core.constant.R;
import com.zhancheng.retail.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录", notes = "传入从微信获得code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "凭证", required = true),
            @ApiImplicitParam(name = "superiorId", value = "上级id")
    })
    public R<Map<String, Object>> wxLogin(String code, Integer superiorId){

        return R.ok(loginService.wxLogin(code, superiorId));
    }
}
