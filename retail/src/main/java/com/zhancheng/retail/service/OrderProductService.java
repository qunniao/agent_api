package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.OrderProduct;

import java.util.List;

/**
 * 订单商品表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
public interface OrderProductService extends IService<OrderProduct> {

    /**
     * 分页查询订单商品表列表
     * @param pageDto 分页参数
     * @return IPage<OrderProduct>
     */
    IPage<OrderProduct> selectPage(PageDto<OrderProduct> pageDto);

    /**
     * 查询订单商品表详情
     * @param oid 主键Id
     * @return
     */
    OrderProduct info(Integer oid);

    /**
     * 添加订单商品表
     * @param orderProduct 订单商品表数据
     * @return Boolean
     */
    Boolean insert(OrderProduct orderProduct);

    /**
     * 修改订单商品表
     * @param orderProduct 订单商品表数据
     * @return Boolean
     */
    Boolean update(OrderProduct orderProduct);

    /**
     * 批量删除订单商品表
     * @param oids 订单商品表id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> oids);
}

