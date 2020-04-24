package com.zhancheng.retail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.core.entity.Store;
import com.zhancheng.core.entity.StoreAd;
import com.zhancheng.core.vo.StoreAdVo;
import com.zhancheng.retail.service.StoreAdService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhancheng.core.dao.StoreAdMapper;

import java.util.List;

/**
 * 店铺广告商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 11:26:07
 */

@Service
public class StoreAdServiceImpl extends ServiceImpl<StoreAdMapper, StoreAd> implements StoreAdService {

    @Override
    public List<StoreAdVo> queryInfo(Integer type) {
        return baseMapper.queryInfo(type);
    }
}