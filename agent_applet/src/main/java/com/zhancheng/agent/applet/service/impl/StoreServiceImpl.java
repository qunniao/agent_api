package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.StoreService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.dao.StoreMapper;
import com.zhancheng.core.entity.Store;

import java.util.List;

/**
 * 店铺
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public IPage<Store> selectPage(PageDto<Store> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<Store>().eq("is_deleted", 0));
    }

    @Override
    public Store info(Integer sid) {

        return baseMapper.selectById(sid);
    }

    @Override
    public Boolean insert(Store store) {
        return  baseMapper.insert(store) > 0;
    }

    @Override
    public Boolean update(Store store) {
        return baseMapper.updateById(store) > 0;
    }

    @Override
    public Boolean delete(List<Integer> sids) {
        return baseMapper.deleteBatchIds(sids) > 0;
    }

}