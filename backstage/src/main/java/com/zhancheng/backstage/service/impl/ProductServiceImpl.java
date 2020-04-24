package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.*;
import com.zhancheng.backstage.util.AddProductUtil;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.*;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.StockTypeEnum;
import com.zhancheng.core.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 产品spu表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private AddProductUtil addProductUtil;

    @Resource
    private ProductImageMapper productImageMapper;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private PtMapper ptMapper;

    @Resource
    private ProductParamMapper productParamMapper;

    @Resource
    private ProductUnitMapper productUnitMapper;

    @Resource
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Resource
    private ProductGuigeNameMapper productGuigeNameMapper;
    @Resource
    private ProductGuigeValueMapper productGuigeValueMapper;

    @Override
    public Map<String,Object> selectPage(PageDto<Product> pageDto, ProductQueryDto productQueryDto) {
        
        Map<String,Object> map = new HashMap<>(2);
        
        IPage<ProductListVo> productPage = baseMapper.queryPage(pageDto.getPage(), productQueryDto);

        if(ObjectUtil.isEmpty(productPage.getRecords())){
            return null;
        }

        map.put("page", productPage);

        // 如果有运费id, 就直接返回，不统计数量
        if(ObjectUtil.isNotNull(productQueryDto.getFreightId())){
            return map;
        }

        productQueryDto.setStatusType(null);
        ProductCountVo productCountVo = baseMapper.countProduct(productQueryDto);
        map.put("count", productCountVo);
        return map;
    }

    @Override
    public ProductVo info(Integer pid) {

        ProductVo productVo = baseMapper.queryInfo(pid);

        if (ObjectUtil.isNull(productVo)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        // 查询分类列表
        List<ProductTypeVo> productTypeList = productTypeMapper.selectListByPid(pid);

        List<ProductImage> productImageList = productImageMapper.selectList(new QueryWrapper<ProductImage>()
                .eq("pid", pid)
                .eq("is_deleted", 0).orderByDesc("sort"));

        // 商品属性集合
        List<ProductParam> productParams = productParamMapper.selectList(new QueryWrapper<ProductParam>()
                .eq("pid", pid)
                .eq("is_deleted", 0).orderByDesc("sort"));

        // 商品箱规
        List<ProductUnit> productUnitList = productUnitMapper.selectList(new QueryWrapper<ProductUnit>()
                .eq("product_id", pid).orderByAsc("level"));

        // 规格名称
        List<ProductGuigeName> productGuigeNameList = productGuigeNameMapper.queryList(pid);

        // 查询sku
        List<ProductSkuListVo> productSkuList = productGuigeSkuMapper.queryList(pid);

        productVo.setProductTypeList(productTypeList)
                .setProductParamList(productParams)
                .setProductUnitList(productUnitList)
                .setProductGuigeNameList(productGuigeNameList)
                .setProductSkuList(productSkuList)
                .setProductImageList(productImageList);
        return productVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(ProductDto productDto) {
        // 添加产品得到产品id
        Product product = new Product();
        BeanUtil.copyProperties(productDto, product);
        baseMapper.insert(product);
        Integer pid = product.getPid();

        ProductImageDto[] productImageDtos = productDto.getProductImageDtos();
        UnitDto[] units = productDto.getUnits();
        ProductParamDto[] productParamDtos = productDto.getProductParamDtos();
        StandardDto[] standardDtos = productDto.getStandardDtos();
        Integer[] typeIds = productDto.getTypeIds();
        ProductGuigeName[] productGuigeNames = productDto.getProductGuigeNames();

        // 添加产品图片
        addProductUtil.addProductImage(productImageDtos, pid);
        // 添加 产品类型
        addProductUtil.addProductType(typeIds, pid);
        // 添加属性组
        if (ObjectUtil.isNotEmpty(productParamDtos)){
            addProductUtil.addProcuctParam(productParamDtos, pid);
        }
        // 添加商品箱规
        if (ObjectUtil.isNotEmpty(units)){
            addProductUtil.addProcuctUnit(units, pid);
        }

        // 添加商品规格名称
        if (ObjectUtil.isNotEmpty(productGuigeNames)){
            addProductUtil.addSkuName(productGuigeNames, pid);
        }

        System.out.println(standardDtos);
        // 添加商品规格
        if (ObjectUtil.isNotEmpty(standardDtos)){
            addProductUtil.addProcuctSku(standardDtos, pid);
            product.setIsSku(true);
        }else {
            product.setIsSku(false);
        }

        product.updateById();

        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(ProductDto productDto) {

        // 添加产品得到产品id
        Product product = new Product();
        BeanUtil.copyProperties(productDto, product);
        product.updateById();
        Integer pid = productDto.getPid();

        ProductImageDto[] productImageDtos = productDto.getProductImageDtos();
        ProductParamDto[] productParamDtos = productDto.getProductParamDtos();
        UnitDto[] units = productDto.getUnits();
        Integer[] typeIds = productDto.getTypeIds();
        StandardDto[] standardDtos = productDto.getStandardDtos();
        ProductGuigeName[] productGuigeNames = productDto.getProductGuigeNames();

        // 修改产品图片
        if (ObjectUtil.isNotEmpty(productImageDtos)){
            productImageMapper.delete(new QueryWrapper<ProductImage>().eq("pid", pid));
            addProductUtil.addProductImage(productImageDtos, pid);
        }

        // 修改 产品类型
        if (ObjectUtil.isNotEmpty(typeIds)){
            ptMapper.delete(new QueryWrapper<Pt>().eq("product_id", pid));
            addProductUtil.addProductType(typeIds, pid);
        }

        // 修改属性组
        if (ObjectUtil.isNotEmpty(productParamDtos)){
            productParamMapper.delete(new QueryWrapper<ProductParam>().eq("pid", pid));
            addProductUtil.addProcuctParam(productParamDtos, pid);
        }
        // 修改商品箱规
        if (ObjectUtil.isNotEmpty(units)){
            productUnitMapper.delete(new QueryWrapper<ProductUnit>().eq("product_id", pid));
            addProductUtil.addProcuctUnit(units, pid);
        }

        // 修改商品规格名称
        if (ObjectUtil.isNotEmpty(productGuigeNames)){

            List<Integer> nidList = productGuigeNameMapper.getNid(pid);
            if (CollectionUtil.isNotEmpty(nidList)) {
                productGuigeNameMapper.deleteBatchIds(nidList);
                productGuigeValueMapper.deleteByNid(nidList);
            }
            addProductUtil.addSkuName(productGuigeNames, pid);
        }
        // 修改商品规格
        if (ObjectUtil.isNotEmpty(standardDtos)){
            productGuigeSkuMapper.delete(new QueryWrapper<ProductGuigeSku>().eq("pid", pid));
            // 清空库存
            product.setTotalStock(0).setAvailableStock(0).setExcessStock(0).setCloudStock(0).setIsSku(true);
            addProductUtil.addProcuctSku(standardDtos, pid);
        }else {
            product.setIsSku(false);
        }

        product.updateById();

        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(List<Integer> pids) {
        return baseMapper.deleteBatchIds(pids) > 0;
    }

    @Override
    public Boolean isExist(String number) {
        Product product = baseMapper.selectOne(new QueryWrapper<Product>()
                .eq("product_number", number)
                .eq("is_deleted", 0));
        ProductGuigeSku productGuigeSku = productGuigeSkuMapper.selectOne(
                new QueryWrapper<ProductGuigeSku>()
                .eq("product_number", number));

        return ObjectUtil.isNull(product) && ObjectUtil.isNull(productGuigeSku) ;
    }
}