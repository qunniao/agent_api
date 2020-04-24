package com.zhancheng.backstage.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AdminService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.AdminDto;
import com.zhancheng.core.entity.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Api(tags = "管理员")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "分页查询管理员列表")
    @GetMapping("/list")
    public R<IPage<Admin>> list(PageDto<Admin> pageDto){

        return R.ok(adminService.selectPage(pageDto));
    }

    @ApiOperation(value = "查询加盟店管理员列表")
    @GetMapping("/franchisee/list")
    public R<List<Admin>> list(){

        return R.ok(adminService.list(new QueryWrapper<Admin>().eq("role_id", 3)));
    }


    @ApiOperation(value = "查询管理员详情")
    @ApiImplicitParam(name = "uid", value = "主键id")
    @GetMapping("/info/{uid}")
    public R<Admin> info(@PathVariable("uid") Integer uid){

        return R.ok(adminService.info(uid));
    }

    @ApiOperation(value = "添加管理员")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "username", value = "用户名",required = true),
                        @ApiImplicitParam(name = "pwd", value = "密码", required = true),
                        @ApiImplicitParam(name = "roleId", value = "角色id：1.总管理员2.普通管理员3.加盟店", required = true),
                        @ApiImplicitParam(name = "nickname", value = "昵称"),
                        @ApiImplicitParam(name = "trueName", value = "真实姓名",required = true),
                        @ApiImplicitParam(name = "cover", value = "头像"),
                        @ApiImplicitParam(name = "phone", value = "手机号", required = true)
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody AdminDto adminDto){

        return R.ok(adminService.insert(adminDto));
    }

    @ApiOperation(value = "修改管理员")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "uid", value = "管理员id",required = true),
                        @ApiImplicitParam(name = "nickname", value = "昵称"),
                        @ApiImplicitParam(name = "cover", value = "头像"),
                        @ApiImplicitParam(name = "phone", value = "手机号"),
                        @ApiImplicitParam(name = "status", value = "状态 0.关闭1.开启 ")
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Admin admin){

        return R.ok(adminService.update(admin));
    }
    @PostMapping("/account")
    @ApiOperation(value = "手机号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name ="code", value = "验证码")
    })
    public R<Map<String, Object>> phoneLogin(String phone, String password, String code){

        return R.ok(adminService.phoneLogin(phone, password, code));
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "管理员修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwd", value = "原密码", required = true),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true)
    })
    public R updatePassword(String pwd, String newPwd) {
        Integer userId = redisTemplate.getAdminUid();
        System.err.println(userId);
        //判断新密码是否为空
        if (StrUtil.isBlank(newPwd) || StrUtil.isBlank(pwd)) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("uid", userId)
                .eq("pwd", SecureUtil.md5(pwd)));
        if (ObjectUtil.isNotNull(admin)) {
            //修改密码
            admin.setPwd(SecureUtil.md5(newPwd));
            return R.ok(admin.updateById());
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @ApiOperation(value = "删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> uids){

        return R.ok(adminService.delete(uids));
    }

}
