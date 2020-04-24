package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductGuigeNameService;
import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.ProductGuigeNameMapper;
import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductGuigeSku;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品规格名称
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Api(tags = "产品规格名称")
@RestController
@RequestMapping("/productGuigeNames")
public class ProductGuigeNameController {

    @Resource
    private ProductGuigeNameMapper productGuigeNameMapper;

    @ApiOperation(value = "分页查询sku名称列表")
    @GetMapping("/info")
    public R<List<ProductGuigeName>> info(Integer nid) {

        return R.ok(productGuigeNameMapper.queryListByNid(nid));
    }
}
