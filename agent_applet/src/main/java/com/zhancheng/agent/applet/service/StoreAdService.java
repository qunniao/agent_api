package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.StoreAd;
import com.zhancheng.core.vo.StoreAdVo;

import java.util.List;

/**
 * 店铺广告商品
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 11:26:07
 */
public interface StoreAdService extends IService<StoreAd> {

    /**
     * 根据类型查询店铺广告
     * @param type
     * @return
     */
    List<StoreAdVo> queryInfo(Integer type);
}

