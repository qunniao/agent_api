package com.zhancheng.agent.applet.controller;

import com.zhancheng.agent.applet.service.ProductTypeService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "查询产品类目列表")
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list(){

        return R.ok(productTypeService.selectList());
    }
}
