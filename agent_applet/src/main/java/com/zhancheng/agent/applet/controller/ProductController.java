package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.ProductService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 产品spu表
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "分页查询产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类型id"),
            @ApiImplicitParam(name = "productType", value = "商品类型0.常规商品1.内购商品",required = true),
    })
    @GetMapping("/list")
    public R<List<Product>> list(ProductQueryDto productQueryDto) {

        return R.ok(productService.queryList(productQueryDto));
    }

    @ApiOperation(value = "查询商品详情")
    @GetMapping("/info")
    public R<Map<String, Object> > info(Integer pid) {

        return R.ok(productService.info(pid));
    }



}
