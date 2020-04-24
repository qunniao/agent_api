package com.zhancheng.backstage.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductTypeService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductType;
import com.zhancheng.core.vo.ProductTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品类目
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Api(tags = "产品类目")
@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {

    @Resource
    private ProductTypeService productTypeService;

    @ApiOperation(value = "分页查询产品类目列表")
    @ApiImplicitParam(name = "sortType", value = "排序方式：默认 sort倒序; 1；时间升序, 2：时间倒序")
    @GetMapping("/page")
    public R<IPage<ProductType>> page(PageDto<ProductType> pageDto, String sortType){

        return R.ok(productTypeService.selectPage(pageDto, sortType));
    }

    @ApiOperation(value = "查询产品类目列表")
    @GetMapping("/list")
    public R<List<ProductType>> list(){

        return R.ok(productTypeService.selectList());
    }
    @ApiOperation(value = "查询商品类型详情")
    @GetMapping("/info/{tid}")
    public R<ProductType> info(@PathVariable Integer tid){

        return R.ok(productTypeService.info(tid));
    }

    @ApiOperation(value = "添加产品类型")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "pid", value = "类目父亲id(0表示一级类目)"),
                        @ApiImplicitParam(name = "pgId", value = "关联属性组id"),
                        @ApiImplicitParam(name = "typeName", value = "类目名称"),
                        @ApiImplicitParam(name = "cover", value = "图片"),
                        @ApiImplicitParam(name = "sort", value = "排序"),
                        @ApiImplicitParam(name = "isHidden", value = "是否隐藏 0:未隐藏; 1:已隐藏，推广商品时不可见 "),
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody ProductType productType){

        return R.ok(productTypeService.insert(productType));
    }

    @ApiOperation(value = "修改产品类型")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "tid", value = "类目id", required = true),
                        @ApiImplicitParam(name = "pid", value = "类目父亲id(0表示一级类目)"),
                        @ApiImplicitParam(name = "pgId", value = "关联属性组id"),
                        @ApiImplicitParam(name = "typeName", value = "类目名称"),
                        @ApiImplicitParam(name = "cover", value = "图片"),
                        @ApiImplicitParam(name = "sort", value = "排序"),
                        @ApiImplicitParam(name = "isHidden", value = "是否隐藏 0:未隐藏; 1:已隐藏，推广商品时不可见 "),
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody ProductType productType){

        return R.ok(productTypeService.update(productType));
    }

    @ApiOperation(value = "删除产品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> tids){

        return R.ok(productTypeService.delete(tids));
    }

    @ApiOperation(value = "隐藏和显示分类接口")
    @PostMapping("/putAway")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "类型id", required = true),
            @ApiImplicitParam(name = "isHidden", value = "是否隐藏 0:未隐藏; 1:已隐藏，推广商品时不可见 ", required = true)
    })
    public R<Boolean> putAway(Integer tid, Integer status){

        ProductType productType = productTypeService.getById(tid);

        if (ObjectUtil.isNull(productType)){
            throw new MyException(CodeMsg.PRODUCT_TYPE_NOT_EXISTED);
        }
        productType.setIsHidden(status);
        return R.ok(productType.updateById());
    }



}
