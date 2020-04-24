package com.zhancheng.core.dao;

import com.zhancheng.core.entity.ProductParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ProductParamVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品参数表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Repository
public interface ProductParamMapper extends BaseMapper<ProductParam> {
	
}
