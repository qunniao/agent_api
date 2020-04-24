package com.zhancheng.agent.applet.controller;

import java.util.List;
import java.util.Map;

import com.zhancheng.agent.applet.service.AgentCartService;
import com.zhancheng.core.dto.AgentDto;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.entity.AgentCart;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartListVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;

import javax.annotation.Resource;

/**
 * 购物车表
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 18:05:12
 */
@Api(tags = "代理购物车")
@RestController
@RequestMapping("/agentCarts")
public class AgentCartController {

    @Resource
    private AgentCartService agentCartService;

    @ApiOperation(value = "分页查询购物车表列表")
    @GetMapping("/queryList")
    public R<List<OrderCartListVo>> queryList(Integer agentId){

        return R.ok(agentCartService.queryList(agentId));
    }

    @ApiOperation(value = "结算购物车商品")
    @ApiImplicitParam(name = "cIds", value = "一个或多个购物车id集合,多个id用逗号分隔")
    @GetMapping("/info")
    public R<List<OrderCartListVo>> queryInfo(@RequestParam List<Integer> cIds){

        return R.ok(agentCartService.queryInfo(cIds));
    }

    @ApiOperation(value = "提交代理购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理人id", required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址"),
            @ApiImplicitParam(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流"),
            @ApiImplicitParam(name = "storePersonId", value = "店铺所属人id"),
            @ApiImplicitParam(name = "freight", value = "运费"),
            @ApiImplicitParam(name = "discount", value = "优惠金额"),
            @ApiImplicitParam(name = "isCloud", value = "是否云订单：1.是0.否",required = true),
            @ApiImplicitParam(name = "isOpen", value = "是否拆单：1.是0.否"),
            @ApiImplicitParam(name = "isFirst", value = "是否首进1.是0.否"),
            @ApiImplicitParam(name = "remark", value = "留言"),
            @ApiImplicitParam(name = "cidList", value = "购物车id集合,如果是从购物车提交的,就传入List<Integer> cidList"),
    })
    @PostMapping("/submitCart")
    public R<Integer> submitCart(@RequestBody AgentOrderDto agentOrderDto){

        return R.ok(agentCartService.submitCart(agentOrderDto));
    }

    @ApiOperation(value = "添加代理购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id", required = true),
            @ApiImplicitParam(name = "pid", value = "商品Id", required = true),
            @ApiImplicitParam(name = "skuId", value = "skuId"),
            @ApiImplicitParam(name = "productName", value = "商品名", required = true),
            @ApiImplicitParam(name = "productNum", value = "商品数量", required = true),
            @ApiImplicitParam(name = "productPrice", value = "商品单价", required = true)
    })
    @PostMapping("/insertOrUpdate")
    public R<Boolean> insertOrUpdate(@RequestBody AgentCart agentCart){

        return R.ok(agentCartService.insertOrUpdate(agentCart));
    }

    @ApiOperation(value = "添加或者减少数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "购物车id"),
            @ApiImplicitParam(name = "num", value = "增加后或减少后的数量"),
    })
    @PostMapping("/changeProductNum")
    public R<Boolean> changeProductNum(OrderCartDto orderCartDto){

        return R.ok(agentCartService.changeProductNum(orderCartDto));
    }

    @ApiOperation(value = "删除购物车表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> cids){

        return R.ok(agentCartService.removeByIds(cids));
    }

}
