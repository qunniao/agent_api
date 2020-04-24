package com.zhancheng.core.dao;

import com.zhancheng.core.entity.ProductGuigeName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品规格名称
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Repository
public interface ProductGuigeNameMapper extends BaseMapper<ProductGuigeName> {

    /**
     * 查询 nid
     * @param pid
     * @return
     */
    @Select("SELECT nid FROM `zc_product_guige_name` WHERE pid = #{pid}")
    List<Integer> getNid(@Param("pid") Integer pid);

    /**
     * 根据商品id查询规格名称
     * @param pid 商品id
     * @return List<ProductGuigeName>
     */
    List<ProductGuigeName> queryList(Integer pid);

    /**
     * 根据商品id查询规格名称
     * @param nid 查询sku名称
     * @return List<ProductGuigeName>
     */
    List<ProductGuigeName> queryListByNid(Integer nid);
}
