package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductGuigeSku;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 产品sku表
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Api(tags = "产品sku表")
@RestController
@RequestMapping("/productGuigeSkus")
public class ProductGuigeSkuController {



}
