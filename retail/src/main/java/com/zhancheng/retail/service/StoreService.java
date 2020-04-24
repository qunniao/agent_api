package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Store;

import java.util.List;

/**
 * 店铺
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
public interface StoreService extends IService<Store> {

    /**
     * 查询店铺详情
     * @param agentId 代理Id
     * @param uid 用户Id
     * @return
     */
    Store info(Integer agentId, Integer uid);

}

