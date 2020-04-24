package com.zhancheng.core.dao;

import com.zhancheng.core.entity.ProductGuigeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品规格值
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Repository
public interface ProductGuigeValueMapper extends BaseMapper<ProductGuigeValue> {

    Boolean deleteByNid(@Param("nidList") List<Integer> nidList);
}
