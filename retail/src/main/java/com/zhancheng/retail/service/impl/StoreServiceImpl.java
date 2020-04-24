package com.zhancheng.retail.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.StoreMapper;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.Store;
import com.zhancheng.core.entity.StoreVisitor;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.util.StoreUtil;
import com.zhancheng.retail.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private StoreUtil storeUtil;

    @Override
    public Store info(Integer agentId, Integer uid) {

        User user = userMapper.selectById(uid);
        Store store;
        if (user.getIsLock() && ObjectUtil.isNotNull(user.getSuperior())){
            store = storeMapper.selectOne(new QueryWrapper<Store>().eq("agent_id", user.getSuperior()));
        }else {
            store = storeMapper.selectOne(new QueryWrapper<Store>().eq("agent_id", agentId));
        }

        // 添加访客记录
        storeUtil.addStoreVisitor(store, uid);

        return store;
    }

}