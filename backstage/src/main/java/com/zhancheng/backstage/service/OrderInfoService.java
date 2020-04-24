package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.BackOrderQueryDto;
import com.zhancheng.core.dto.OrderInfoDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.vo.OrderBackListVo;
import com.zhancheng.core.vo.OrderInfoVo;

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
    IPage<OrderBackListVo> selectPage(PageDto<OrderInfo> pageDto, BackOrderQueryDto backOrderQueryDto);

    /**
     * 查询订单表详情
     * @param oid 主键Id
     * @return
     */
    OrderInfoVo info(Integer oid);

    /**
     * 查询订单表详情
     * @param orderNumber 订单号
     * @return
     */
    OrderInfoVo details(String  orderNumber);

    /**
     * 发货
     * @param oid 主键Id
     * @return
     */
    Boolean shipments(Integer oid);
}

