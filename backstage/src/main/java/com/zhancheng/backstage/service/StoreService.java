package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.Store;

/**
 * @author BianShuHeng
 * @decription
 * @project StoreService
 * @date 2019/10/31 16:11
 */
public interface StoreService  extends IService<Store> {

   Boolean insertStore(Store store);
}
