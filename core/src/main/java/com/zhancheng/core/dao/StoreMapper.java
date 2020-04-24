package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 店铺
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Repository
public interface StoreMapper extends BaseMapper<Store> {
	
}
