package com.zhancheng.agent.applet.service;

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
     * 分页查询店铺列表
     * @param pageDto 分页参数
     * @return IPage<Store>
     */
    IPage<Store> selectPage(PageDto<Store> pageDto);

    /**
     * 查询店铺详情
     * @param sid 主键Id
     * @return
     */
    Store info(Integer sid);

    /**
     * 添加店铺
     * @param store 店铺数据
     * @return Boolean
     */
    Boolean insert(Store store);

    /**
     * 修改店铺
     * @param store 店铺数据
     * @return Boolean
     */
    Boolean update(Store store);

    /**
     * 批量删除店铺
     * @param sids 店铺id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> sids);
}

