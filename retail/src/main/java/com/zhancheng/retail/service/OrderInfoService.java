package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.OrderInfoDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.vo.OrderListVo;
import com.zhancheng.core.vo.RetailOrderInfoVo;

import java.util.List;

/**
 * 订单表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 分页查询订单表列表
     * @param pageDto 分页参数
     * @return IPage<OrderInfo>
     */
    IPage<OrderInfo> selectPage(PageDto<OrderInfo> pageDto, OrderQueryDto orderQueryDto);

    /**
     * 查询订单表详情
     * @param oid 主键Id
     * @return
     */
    RetailOrderInfoVo info(Integer oid);

    /**
     * 添加订单表
     * @param orderInfoDto 订单表数据
     * @return Boolean
     */
    Integer insert(OrderInfoDto orderInfoDto);

    /**
     * 修改订单表
     * @param orderInfo 订单表数据
     * @return Boolean
     */
    Boolean update(OrderInfo orderInfo);

    /**
     * 批量删除订单表
     * @param oids 订单表id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> oids);

    /**
     * 发货
     * @param oid 订单表id
     * @return Boolean
     */
    Boolean shipments(Integer oid);
}

