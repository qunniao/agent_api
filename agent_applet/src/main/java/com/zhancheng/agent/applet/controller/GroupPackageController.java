package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.GroupPackageService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.GroupPackage;
import com.zhancheng.core.vo.GroupPackageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 产品组合套餐
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
@Api(tags = "产品组合套餐")
@RestController
@RequestMapping("/groupPackages")
public class GroupPackageController {

    @Resource
    private GroupPackageService groupPackageService;

    @ApiOperation(value = "查询等级id查询套餐列表")
    @GetMapping("/list/levelId")
    public R<List<GroupPackage>> queryList(Integer levelId) {

        return R.ok(groupPackageService.queryList(levelId));
    }

    @ApiOperation(value = "查询套餐组合id查询套餐详情")
    @ApiImplicitParam(name = "gid", value = "组合套餐id")
    @GetMapping("/info/{gid}")
    public R<GroupPackageVo> queryInfo(@PathVariable("gid") Integer gid) {

        return R.ok(groupPackageService.queryInfo(gid));
    }

}
