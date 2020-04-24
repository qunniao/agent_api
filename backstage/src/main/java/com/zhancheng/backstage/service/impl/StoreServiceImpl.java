package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.StoreService;
import com.zhancheng.core.dao.StoreMapper;
import com.zhancheng.core.entity.Store;
import org.springframework.stereotype.Service;


/**
 * @author BianShuHeng
 * @decription
 * @project StoreServiceImpl
 * @date 2019/10/31 16:12
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public Boolean insertStore(Store store) {
        return store.insert();
    }
}
