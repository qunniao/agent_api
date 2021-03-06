package com.zhancheng.agent.applet.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.OrderCartService;
import com.zhancheng.agent.applet.service.OrderInfoService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.OrderInfoDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.vo.RetailOrderInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
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
    public R<IPage<OrderInfo>> list(PageDto<OrderInfo> pageDto, OrderQueryDto orderQueryDto) {

        return R.ok(orderInfoService.selectPage(pageDto, orderQueryDto));
    }

    @ApiOperation(value = "查询订单表详情")
    @ApiImplicitParam(name = "oid", value = "主键id")
    @GetMapping("/info/{oid}")
    public R<RetailOrderInfoVo> info(@PathVariable("oid") Integer oid) {
        return R.ok(orderInfoService.info(oid));
    }

    @ApiOperation(value = "添加订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderType", value = "订单类型：1.采购2.零售3.提货4.内购5.换货", required = true),
            @ApiImplicitParam(name = "agentId", value = "代理人id", required = true),
            @ApiImplicitParam(name = "inviter", value = "邀请人id",required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址"),
            @ApiImplicitParam(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流"),
            @ApiImplicitParam(name = "storePersonId", value = "店铺所属人id"),
            @ApiImplicitParam(name = "freight", value = "运费"),
            @ApiImplicitParam(name = "discount", value = "优惠金额"),
            @ApiImplicitParam(name = "isCloud", value = "是否云订单：1.是0.否"),
            @ApiImplicitParam(name = "isOpen", value = "是否拆单：1.是0.否"),
            @ApiImplicitParam(name = "isFirst", value = "是否首进"),
            @ApiImplicitParam(name = "isForward", value = "是否转代发"),
            @ApiImplicitParam(name = "isCloudForward", value = "是否云代发"),
            @ApiImplicitParam(name = "remark", value = "留言"),
            @ApiImplicitParam(name = "orderProductDtoList", value = "订单商品集合",required = true)
    })
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public R<Integer> save(@RequestBody OrderInfoDto orderInfoDto) {

        return R.ok(orderInfoService.insert(orderInfoDto));
    }

    @ApiOperation(value = "修改订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "订单id"),
            @ApiImplicitParam(name = "type", value = "类型：1.总部订单2.代理订单"),
            @ApiImplicitParam(name = "orderType", value = "订单类型：1.采购2.零售3.提货4.内购5.换货", required = true),
            @ApiImplicitParam(name = "agentId", value = "代理人id，是代理就传代理id"),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址"),
            @ApiImplicitParam(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流"),
            @ApiImplicitParam(name = "totalPrice", value = "总价格"),
            @ApiImplicitParam(name = "freight", value = "运费"),
            @ApiImplicitParam(name = "discount", value = "优惠金额"),
            @ApiImplicitParam(name = "isCloud", value = "是否云订单：1.是0.否"),
            @ApiImplicitParam(name = "isOpne", value = "是否拆单：1.是0.否"),
            @ApiImplicitParam(name = "isFirst", value = "是否首进"),
            @ApiImplicitParam(name = "isForward", value = "是否转代发"),
            @ApiImplicitParam(name = "isCloudForward", value = "是否云代发"),
            @ApiImplicitParam(name = "remark", value = "留言"),
            @ApiImplicitParam(name = "orderProductDtoList", value = "订单商品集合")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody OrderInfo orderInfo) {

        return R.ok(orderInfoService.update(orderInfo));
    }

    @ApiOperation(value = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "订单id"),
    })
    @DeleteMapping("/delete/{oid}")
    public R<Boolean> delete(@PathVariable Integer oid) {

        return R.ok(orderInfoService.delete(oid));
    }

}
