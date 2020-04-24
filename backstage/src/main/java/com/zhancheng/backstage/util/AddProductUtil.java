package com.zhancheng.backstage.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.*;
import com.zhancheng.core.dto.*;
import com.zhancheng.core.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project AddProductUtil
 * @date 2019/10/17 18:23
 */
@Component
public class AddProductUtil {

    @Resource
    private PtService ptService;

    @Resource
    private ProductImageService productImageService;

    @Resource
    private ProductParamService productParamService;

    @Resource
    private ProductUnitService productUnitService;

    @Resource
    private ProductGuigeSkuService productGuigeSkuService;

    @Resource
    private ProductGuigeNameService productGuigeNameService;
    @Resource
    private ProductGuigeValueService productGuigeValueService;

    /**
     * 添加产品图片
     *
     * @param productImageDtos
     * @param pid
     * @return
     */
    public Boolean addProductImage(ProductImageDto[] productImageDtos, Integer pid) {
        List<ProductImage> productImageList = new LinkedList<>();
        for (ProductImageDto productImageDto : productImageDtos) {
            ProductImage productImage = new ProductImage();
            productImage.setPid(pid).setUrl(productImageDto.getUrl()).setSort(productImageDto.getSort());
            productImageList.add(productImage);
        }
        return productImageService.saveBatch(productImageList);
    }

    /**
     * 添加 产品类型
     *
     * @param typeIds
     * @param pid
     * @return
     */
    public Boolean addProductType(Integer[] typeIds, Integer pid) {

        List<Pt> ptList = new LinkedList<>();
        System.err.println(ptList);
        for (Integer typeId : typeIds) {
            Pt pt = new Pt();
            pt.setProductId(pid).setTypeId(typeId);

            ptList.add(pt);
        }
        return ptService.saveBatch(ptList);
    }

    /**
     * 添加属性组
     *
     * @param productParamDtos
     * @param pid
     * @return
     */
    public Boolean addProcuctParam(ProductParamDto[] productParamDtos, Integer pid) {
        List<ProductParam> productParamList = new LinkedList<>();
        for (ProductParamDto productParamDto : productParamDtos) {
            ProductParam productParam = new ProductParam();
            BeanUtil.copyProperties(productParamDto, productParam);
            productParam.setPid(pid);
            productParamList.add(productParam);
        }
        return productParamService.saveBatch(productParamList);
    }

    public Boolean addProcuctUnit(UnitDto[] units, Integer pid) {

        List<ProductUnit> productUnitList = new ArrayList<>();
        for (UnitDto unit : units) {
            ProductUnit productUnit = new ProductUnit();
            BeanUtil.copyProperties(unit, productUnit);
            productUnit.setProductId(pid);
            productUnitList.add(productUnit);
        }
        return productUnitService.saveBatch(productUnitList);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean addSkuName(ProductGuigeName[] productGuigeNames, Integer pid) {

        List<ProductGuigeName> productGuigeNameList = new ArrayList<>();
        List<ProductGuigeValue> productGuigeValueList = new ArrayList<>();

        for (ProductGuigeName productGuigeName : productGuigeNames) {
            // 添加 pid
            productGuigeName.setPid(pid);
            productGuigeNameList.add(productGuigeName);
        }
        boolean save = productGuigeNameService.saveBatch(productGuigeNameList);

        for (ProductGuigeName productGuigeName : productGuigeNameList) {
            Integer nid = productGuigeName.getNid();
            ProductGuigeValue[] productGuigeValues = productGuigeName.getProductGuigeValues();
            System.err.println(productGuigeValues);
            for (ProductGuigeValue productGuigeValue : productGuigeValues) {
                productGuigeValue.setNid(nid);
                productGuigeValueList.add(productGuigeValue);
            }
        }
        boolean save1 = productGuigeValueService.saveBatch(productGuigeValueList);

        return save && save1;
    }

    /**
     * 添加商品规格
     *
     * @param standardDtos
     * @param pid
     * @return
     */
    public Boolean addProcuctSku(StandardDto[] standardDtos, Integer pid) {

        List<ProductGuigeSku> productGuigeSkuList = new ArrayList<>();
        for (StandardDto standardDto : standardDtos) {

            ProductGuigeSku productGuigeSku = new ProductGuigeSku();
            BeanUtil.copyProperties(standardDto, productGuigeSku);
            productGuigeSku.setPid(pid);
            productGuigeSkuList.add(productGuigeSku);
        }

        return productGuigeSkuService.saveBatch(productGuigeSkuList);
    }

}
