package com.zhancheng.backstage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.ProductDto;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductListVo;
import com.zhancheng.core.vo.ProductVo;
import io.swagger.annotations.*;
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

    @Resource
    private ProductService productService;

    @ApiOperation(value = "分页查询产品列表")
    @GetMapping("/list")
    public R<Map<String,Object>> list(PageDto<Product> pageDto, ProductQueryDto productQueryDto) {

        System.err.println(productQueryDto);
        return R.ok(productService.selectPage(pageDto, productQueryDto));
    }

    @ApiOperation(value = "查询产品详情")
    @ApiImplicitParam(name = "pid", value = "主键id")
    @GetMapping("/info/{pid}")
    public R<ProductVo> info(@PathVariable("pid") Integer pid) {

        return R.ok(productService.info(pid));
    }

    @ApiOperation(value = "添加产品", notes = "详情请看model")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productType", value = "商品类型0.常规商品1.内购商品"),
            @ApiImplicitParam(name = "productNumber", value = "商品货号"),
            @ApiImplicitParam(name = "sid", value = "店铺id"),
            @ApiImplicitParam(name = "productName", value = "产品名称"),
            @ApiImplicitParam(name = "stockPrice", value = "进货价"),
            @ApiImplicitParam(name = "cover", value = "商品主图"),
            @ApiImplicitParam(name = "productIntro", value = "产品描述"),
            @ApiImplicitParam(name = "price", value = "商品零售价"),
            @ApiImplicitParam(name = "marketPrice", value = "商品市场价格"),
            @ApiImplicitParam(name = "weight", value = "商品重量为不能超过9个字符的正整数"),
            @ApiImplicitParam(name = "showStock", value = "下单时是否显示库存1.是0.否"),
            @ApiImplicitParam(name = "content", value = "手机端详情"),
            @ApiImplicitParam(name = "retailFreightId", value = "零售运费模板id"),
            @ApiImplicitParam(name = "agentFreightId", value = "代理运费模板id"),
            @ApiImplicitParam(name = "cloudFreightId", value = "云仓库提货运费模板id"),
            @ApiImplicitParam(name = "sales", value = "基础销量：系统虚拟的销量基数"),
            @ApiImplicitParam(name = "agentBuy", value = "代理购买方式0.不代理1.物流进货2.云仓库储货3.物流和云仓库"),
            @ApiImplicitParam(name = "isUniformFreight", value = "是否统一运费：0.否1.是"),
            @ApiImplicitParam(name = "sort", value = "商品排序"),
            @ApiImplicitParam(name = "putawayTime", value = "定时上架时间戳 0.否"),
            @ApiImplicitParam(name = "soldoutTime", value = "定时下架时间戳 0.否"),
            @ApiImplicitParam(name = "typeIds", value = "多个分类id数组: Integer[]"),
            @ApiImplicitParam(name = "productParams", value = "产品属性数组: ProductParam[]"),
            @ApiImplicitParam(name = "units", value = "商品箱规: UnitDto[]"),
            @ApiImplicitParam(name = "standardDtos", value = "商品规格: StandardDto[]"),
            @ApiImplicitParam(name = "productImageDtos", value = "商品图片", example = "productImageDtos[]")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody ProductDto productDto) {

        return R.ok(productService.insert(productDto));
    }

    @ApiOperation(value = "修改产品", notes = "详情请看model")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "商品id"),
            @ApiImplicitParam(name = "productType", value = "商品类型0.常规商品1.内购商品"),
            @ApiImplicitParam(name = "productNumber", value = "商品货号"),
            @ApiImplicitParam(name = "sid", value = "店铺id"),
            @ApiImplicitParam(name = "productName", value = "商品名称"),
            @ApiImplicitParam(name = "cover", value = "商品主图"),
            @ApiImplicitParam(name = "productIntro", value = "商品描述"),
            @ApiImplicitParam(name = "price", value = "商品零售价"),
            @ApiImplicitParam(name = "marketPrice", value = "商品市场价格"),
            @ApiImplicitParam(name = "weight", value = "商品重量为不能超过9个字符的正整数"),
            @ApiImplicitParam(name = "showStock", value = "下单时是否显示库存1.是0.否"),
            @ApiImplicitParam(name = "content", value = "手机端详情"),
            @ApiImplicitParam(name = "retailFreightId", value = "零售运费模板id"),
            @ApiImplicitParam(name = "agentFreightId", value = "代理运费模板id"),
            @ApiImplicitParam(name = "cloudFreightId", value = "云仓库提货运费模板id"),
            @ApiImplicitParam(name = "isUniformFreight", value = "是否统一运费：0.否1.是"),
            @ApiImplicitParam(name = "sales", value = "基础销量：系统虚拟的销量基数"),
            @ApiImplicitParam(name = "agentBuy", value = "代理购买方式0.不代理1.物流进货2.云仓库储货3.物流和云仓库"),
            @ApiImplicitParam(name = "sort", value = "商品排序"),
            @ApiImplicitParam(name = "putawayTime", value = "定时上架时间戳 0.否"),
            @ApiImplicitParam(name = "soldoutTime", value = "定时下架时间戳 0.否"),
            @ApiImplicitParam(name = "typeIds", value = "多个分类id数组: Integer[]"),
            @ApiImplicitParam(name = "productParams", value = "产品属性数组: ProductParam[]"),
            @ApiImplicitParam(name = "units", value = "商品箱规: UnitDto[]"),
            @ApiImplicitParam(name = "standardDtos", value = "商品规格: StandardDto[]"),
            @ApiImplicitParam(name = "productImageDtos", value = "商品图片", example = "productImageDtos[]")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody ProductDto productDto) {

        return R.ok(productService.update(productDto));
    }

    @ApiOperation(value = "删除产品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> pids) {

        return R.ok(productService.delete(pids));
    }

    @ApiOperation(value = "上架和下架商品接口")
    @PostMapping("/putAway")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "产品id", required = true),
            @ApiImplicitParam(name = "status", value = "产品状态：0上架中，-1已下架", required = true)
    })
    public R<Boolean> putAway(Integer pid, Integer status){

        Product product = productService.getById(pid);

        if (ObjectUtil.isNull(product)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }
        product.setStatus(status);
        return R.ok(product.updateById());
    }

    @ApiOperation(value = "查询货号是否存在 ：true 表示货号不存在； false 表示货号已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "商品货号或者sku货号", required = true)
    })
    @PostMapping("/isExist")
    public R<Boolean> isExist(String number){
        return R.ok(productService.isExist(number));
    }

}
