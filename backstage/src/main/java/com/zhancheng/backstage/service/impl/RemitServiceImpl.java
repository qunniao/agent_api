package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.RemitService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.dao.RemitMapper;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.entity.Remit;
import com.zhancheng.core.enums.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 打款审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */

@Service
public class RemitServiceImpl extends ServiceImpl<RemitMapper, Remit> implements RemitService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public IPage<Remit> queryPage(PageDto<Remit> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<Remit>().orderByDesc("gmt_modified"));
    }

    @Override
    public Remit queryInfo(Integer id) {
        return  baseMapper.selectById(id);
    }

    @Override
    public Boolean ratifyVoucher(Integer id, Integer auditorType) {

        Remit remitInfo = baseMapper.selectById(id);

        if (ObjectUtil.isNull(remitInfo)){
            throw new MyException(CodeMsg.REMIT_IS_NULL);
        }

        // 订单号
        String orderNumber = remitInfo.getOrderNumber();

        OrderInfo orderInfo = orderInfoMapper.selectOne(new QueryWrapper<OrderInfo>()
                .eq("order_number", orderNumber).eq("is_deleted", 0));

        if (ObjectUtil.isNull(orderInfo) || orderInfo.getOrderState() < 5){
            throw new MyException(CodeMsg.ORDER_ERROR);
        }

        orderInfo.setOrderState(OrderStateEnum.AWAIT_SEND_OUT.getState());

        remitInfo.setAuditorType(auditorType);
        remitInfo.setReviewState(1);

        return remitInfo.updateById() && orderInfo.updateById();
    }

    @Override
    public Boolean rejectVoucher(Integer id, String refuseReason, Integer auditorType) {

        Remit remitInfo = baseMapper.selectById(id);

        if (ObjectUtil.isNull(remitInfo)){
            throw new MyException(CodeMsg.REMIT_IS_NULL);
        }

        remitInfo.setRefuseReason(refuseReason);

        remitInfo.setAuditorType(auditorType);
        remitInfo.setReviewState(2);
        return remitInfo.updateById();
    }

}