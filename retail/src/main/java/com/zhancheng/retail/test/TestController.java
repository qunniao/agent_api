package com.zhancheng.retail.test;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.dto.ProductDto;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.util.StoreUtil;
import com.zhancheng.core.vo.ProductCountVo;
import com.zhancheng.core.vo.ProductListVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @GetMapping("/3")
    public Object test2(HttpServletRequest request) {

        String clientIP = ServletUtil.getClientIP(request);
        // 根据ip地址获取城市信息
        String address = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + clientIP);
        System.out.println(clientIP);

        System.out.println(address);

        Map map = new HashMap<>();
        map.put("clientIP", clientIP);
        map.put("address", address);
        return map;
    }
}
