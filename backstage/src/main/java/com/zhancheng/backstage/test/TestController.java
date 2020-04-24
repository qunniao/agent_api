package com.zhancheng.backstage.test;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.util.AddProductUtil;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.dto.ProductDto;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductType;
import com.zhancheng.core.util.StoreUtil;
import com.zhancheng.core.vo.ProductCountVo;
import com.zhancheng.core.vo.ProductListVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project TestController
 * @date 2019/10/19 10:13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private AddProductUtil addProductUtil;

    @Resource
    private StoreUtil storeUtil;

    @GetMapping("/1")
    public Object queryProductTest(PageDto<Product> pageDto, ProductQueryDto productQueryDto) {

        Map<String, Object> map = new HashMap<>(2);
        IPage<ProductListVo> productListVoPage = productMapper.queryPage(pageDto.getPage(), productQueryDto);
        System.err.println(productListVoPage);
        ProductCountVo productCountVo = productMapper.countProduct(productQueryDto);

        System.err.println(productCountVo);
        map.put("page", productListVoPage);
        map.put("maps", productCountVo);
       return map;
    }

    @PutMapping("/2")
    public Object test2(@RequestBody ProductDto productDto) {

        System.err.println(productDto);

      return  addProductUtil.addSkuName(productDto.getProductGuigeNames(), 1);
    }
    @GetMapping("/3")
    public Object test2(HttpServletRequest request) {


        String clientIP = ServletUtil.getClientIP(request);
        System.out.println(clientIP);

        return clientIP;
    }
}
