package com.zhancheng.agent.applet.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.UserAddressService;
import com.zhancheng.core.constant.R;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.zhancheng.core.entity.UserAddress;

import javax.annotation.Resource;


/**
 * 用户地址
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */
@Api(tags = "用户地址")
@RestController
@RequestMapping("/userAddresss")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @ApiOperation(value = "分页查询用户地址列表")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/list/{uid}")
    public R<List<UserAddress>> list(@PathVariable Integer uid){

        return R.ok(userAddressService.list(new QueryWrapper<UserAddress>().eq("uid", uid)
                .orderByDesc("is_default_address").orderByDesc("gmt_modified")));
    }

    @ApiOperation(value = "查询用户地址详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<UserAddress> info(@PathVariable("id") Integer id){

        return R.ok(userAddressService.info(id));
    }

    @ApiOperation(value = "添加用户地址")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "用户地址id"),
                        @ApiImplicitParam(name = "uid", value = "用户 id"),
                        @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
                        @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
                        @ApiImplicitParam(name = "province", value = "省"),
                        @ApiImplicitParam(name = "city", value = "市"),
                        @ApiImplicitParam(name = "county", value = "区"),
                        @ApiImplicitParam(name = "provinceId", value = "省id"),
                        @ApiImplicitParam(name = "cityId", value = "城市id"),
                        @ApiImplicitParam(name = "countyId", value = "区域id"),
                        @ApiImplicitParam(name = "contactAddress", value = "地址"),
                        @ApiImplicitParam(name = "longitude", value = "经度"),
                        @ApiImplicitParam(name = "latitude", value = "纬度"),
                        @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址,0为不是，1为是")
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody UserAddress userAddress){

        return R.ok(userAddressService.insert(userAddress));
    }

    @ApiOperation(value = "修改用户地址")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "用户地址id"),
                        @ApiImplicitParam(name = "uid", value = "用户 id"),
                        @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
                        @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
                        @ApiImplicitParam(name = "province", value = "省"),
                        @ApiImplicitParam(name = "city", value = "市"),
                        @ApiImplicitParam(name = "county", value = "区"),
                        @ApiImplicitParam(name = "provinceId", value = "省id"),
                        @ApiImplicitParam(name = "cityId", value = "城市id"),
                        @ApiImplicitParam(name = "countyId", value = "区域id"),
                        @ApiImplicitParam(name = "contactAddress", value = "地址"),
                        @ApiImplicitParam(name = "longitude", value = "经度"),
                        @ApiImplicitParam(name = "latitude", value = "纬度"),
                        @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址,0为不是，1为是")
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody UserAddress userAddress){

        return R.ok(userAddressService.update(userAddress));
    }

    @ApiOperation(value = "查询默认收货地址")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/queryDefault/{uid}")
    public R<UserAddress> queryDefault(@PathVariable Integer uid){

        return R.ok(userAddressService.queryDefault(uid));
    }

    @ApiOperation(value = "删除用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(userAddressService.delete(ids));
    }

}
