package com.zhancheng.agent.applet.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.OrderProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.entity.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单商品表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */

@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {

    @Override
    public IPage<OrderProduct> selectPage(PageDto<OrderProduct> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<OrderProduct>().eq("is_deleted", 0));
    }

    @Override
    public OrderProduct info(Integer oid) {

        return baseMapper.selectById(oid);
    }

    @Override
    public Boolean insert(OrderProduct orderProduct) {
        return  baseMapper.insert(orderProduct) > 0;
    }

    @Override
    public Boolean update(OrderProduct orderProduct) {
        return baseMapper.updateById(orderProduct) > 0;
    }

    @Override
    public Boolean delete(List<Integer> oids) {
        return baseMapper.deleteBatchIds(oids) > 0;
    }

}