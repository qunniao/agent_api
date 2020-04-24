package com.zhancheng.core.dao;

import com.zhancheng.core.entity.StoreAd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.StoreAdVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 店铺广告商品
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 11:26:07
 */
@Repository
public interface StoreAdMapper extends BaseMapper<StoreAd> {

   List<StoreAdVo> queryInfo(Integer type);
}
