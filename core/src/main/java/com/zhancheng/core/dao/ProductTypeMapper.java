package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ProductTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品类目
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Repository
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    /**
     * 分页查询分类列表
     * @param page 分页信息
     * @param sortType 排序类型
     * @return IPage<ProductType>
     */
   IPage<ProductType> queryTypePage(Page page, @Param("sortType") String sortType);

    /**
     * 查询分类列表
     * @return List<ProductType>
     */
   List<ProductType> queryTypeByList();

    /**
     * 查询分类列表
     * @param pid 商品id
     * @return List<ProductType>
     */
   Map<String, Object> queryType(Integer pid);


    /**
     * 查询分类列表
     * @param pid 商品id
     * @return IPage<ProductType>
     */
   List<ProductTypeVo> selectListByPid(Integer pid);

    /** 根据类型id 统计下级数量
     * 统计下级
     * @param tid 类型id
     * @return
     */
   Integer countSubordinate(Integer tid);

    /** 根据类型id 统计下级数量
     * 统计下级
     * @return
     */
    List<Map<String, Object>> countTypeList();

}
