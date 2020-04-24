package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.OrderInfoService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.BackOrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.vo.OrderBackListVo;
import com.zhancheng.core.vo.OrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单表
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-28 11:53:16
 */
@Api(tags = "订单表")
@RestController
@RequestMapping("/orderInfos")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "分页查询订单表列表")
    @GetMapping("/list")
    public R<IPage<OrderBackListVo>> list(PageDto<OrderInfo> pageDto, BackOrderQueryDto backOrderQueryDto) {

        return R.ok(orderInfoService.selectPage(pageDto, backOrderQueryDto));
    }

    @ApiOperation(value = "查询订单表详情")
    @ApiImplicitParam(name = "oid", value = "主键id")
    @GetMapping("/info/{oid}")
    public R<OrderInfoVo> info(@PathVariable("oid") Integer oid) {
        return R.ok(orderInfoService.info(oid));
    }

    @ApiOperation(value = "查询订单表详情")
    @ApiImplicitParam(name = "orderNumber", value = "订单号")
    @GetMapping("/details/{orderNumber}")
    public R<OrderInfoVo> details(@PathVariable("orderNumber") String orderNumber) {
        return R.ok(orderInfoService.details(orderNumber));
    }

    @ApiOperation(value = "发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "订单id"),
    })
    @PutMapping("/shipments/{oid}")
    public R<Boolean> shipments(@PathVariable Integer oid) {

        return R.ok(orderInfoService.shipments(oid));
    }

    @ApiOperation(value = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> oids) {

        return R.ok(orderInfoService.removeByIds(oids));
    }
}
