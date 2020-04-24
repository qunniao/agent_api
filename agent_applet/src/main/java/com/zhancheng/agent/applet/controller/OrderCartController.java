package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.OrderCartService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车表
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Api(tags = "用户购物车购物车")
@RestController
@RequestMapping("/orderCarts")
public class OrderCartController {

    @Resource
    private OrderCartService orderCartService;

    @ApiOperation(value = "分页查询购物车表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户Id")
    })
    @GetMapping("/page")
    public R<IPage<OrderCartListVo>> page(PageDto<OrderCart> pageDto, Integer uid){

        return R.ok(orderCartService.selectPage(pageDto, uid));
    }

    @ApiOperation(value = "提交代理购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理id", required = true),
            @ApiImplicitParam(name = "storeId", value = "店铺id", required = true),
            @ApiImplicitParam(name = "storePersonId", value = "店铺所属人id", required = true),
            @ApiImplicitParam(name = "orderType", value = "订单状态", required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址"),
            @ApiImplicitParam(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流"),
            @ApiImplicitParam(name = "freight", value = "运费"),
            @ApiImplicitParam(name = "discount", value = "优惠金额"),
            @ApiImplicitParam(name = "isCloud", value = "是否云订单：1.是0.否"),
            @ApiImplicitParam(name = "isOpen", value = "是否首进1.是0.否"),
            @ApiImplicitParam(name = "isFirst", value = "是否转代发1.是0.否"),
            @ApiImplicitParam(name = "isForward", value = "是否拆单：1.是0.否"),
            @ApiImplicitParam(name = "isCloudForward", value = "是否云代发 1.是0.否"),
            @ApiImplicitParam(name = "remark", value = "留言"),
            @ApiImplicitParam(name = "cIdList", value = "购物车id集合,传入List<Integer> cIdList"),
    })
    @PostMapping("/submitCart")
    public R<Integer> submitCart(@RequestBody AgentOrderDto agentOrderDto){

        return R.ok(orderCartService.submitCart(agentOrderDto));
    }
}
