package com.zhancheng.backstage.controller;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.RemitService;
import com.zhancheng.core.constant.R;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Remit;

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

    @ApiOperation(value = "分页查询打款审核列表")
    @GetMapping("/list")
    public R<IPage<Remit>> list(PageDto<Remit> pageDto){

        return R.ok(remitService.queryPage(pageDto));
    }

    @ApiOperation(value = "查询打款审核详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Remit> queryInfo(@PathVariable("id") Integer id){

        return R.ok(remitService.queryInfo(id));
    }

    @ApiOperation(value = "通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true),
            @ApiImplicitParam(name = "auditorType", value = "审核人：1.总部2.代理3.自动审核", required = true)
    })

    @PostMapping("/ratifyVoucher")
    public R<Boolean> ratifyVoucher(Integer id, Integer auditorType){

        return R.ok(remitService.ratifyVoucher(id, auditorType));
    }
    @ApiOperation(value = "驳回审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true),
            @ApiImplicitParam(name = "refuseReason", value = "驳回原因,驳回时传参"),
            @ApiImplicitParam(name = "auditorType", value = "审核人：1.总部2.代理3.自动审核", required = true)
    })

    @PostMapping("/rejectVoucher")
    public R<Boolean> rejectVoucher(Integer id, String refuseReason, Integer auditorType){

        return R.ok(remitService.rejectVoucher(id, refuseReason, auditorType));
    }
}
