package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.StoreVisitorQueryDto;
import com.zhancheng.core.entity.StoreVisitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.StoreVisitorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 店铺访客记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */
@Repository
public interface StoreVisitorMapper extends BaseMapper<StoreVisitor> {

   IPage<StoreVisitorVo> queryPage(Page page, @Param("query") StoreVisitorQueryDto storeVisitorQueryDto);
	
}
