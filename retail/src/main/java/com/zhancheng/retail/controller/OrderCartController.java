package com.zhancheng.retail.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartListVo;
import com.zhancheng.retail.service.OrderCartService;
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
@Api(tags = "购物车表")
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

    @ApiOperation(value = "结算购物车商品")
    @ApiImplicitParam(name = "cIds", value = "一个或多个购物车id字符串,多个id用逗号分隔" ,example = "1,2,3")
    @GetMapping("/info")
    public R<List<OrderCartListVo>> info(@RequestParam List<Integer> cIds){

        return R.ok(orderCartService.info(cIds));
    }

    @ApiOperation(value = "添加或者编辑购物车表")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "sid", value = "店铺id"),
                        @ApiImplicitParam(name = "uid", value = "用户Id"),
                        @ApiImplicitParam(name = "skuId", value = "库存Id"),
                        @ApiImplicitParam(name = "spuId", value = "商品Id", required = true),
                        @ApiImplicitParam(name = "productName", value = "商品名"),
                        @ApiImplicitParam(name = "productNum", value = "商品数量"),
                        @ApiImplicitParam(name = "productPrice", value = "商品单价")
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody OrderCart orderCart){

        return R.ok(orderCartService.insertOrUpdate(orderCart));
    }

    @ApiOperation(value = "添加或者减少数量")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "cid", value = "购物车id"),
                        @ApiImplicitParam(name = "num", value = "增加后或减少后的数量"),
            })
    @PostMapping("/changeProductNum")
    public R<Boolean> changeProductNum(OrderCartDto orderCartDto){

        return R.ok(orderCartService.changeProductNum(orderCartDto));
    }


    @ApiOperation(value = "删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> cids){

        return R.ok(orderCartService.delete(cids));
    }

    @ApiOperation(value = "提交购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentId", value = "代理人id", required = true),
            @ApiImplicitParam(name = "uId", value = "代理人id", required = true),
            @ApiImplicitParam(name = "storeId", value = "店铺id", required = true),
            @ApiImplicitParam(name = "orderType", value = "订单状态", required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址"),
            @ApiImplicitParam(name = "sendWay", value = "配送方式1.快递2.自提3.无需物流"),
            @ApiImplicitParam(name = "storePersonId", value = "店铺所属人id"),
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
