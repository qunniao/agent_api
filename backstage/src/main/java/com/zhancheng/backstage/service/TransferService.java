package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.TransferListVo;

/**
 * 转账记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 14:40:29
 */
public interface TransferService extends IService<Transfer> {

    /**
     * 分页查询订单表列表
     * @param pageDto 分页参数
     * @return IPage<OrderInfo>
     */
    IPage<TransferListVo> selectPage(PageDto pageDto, TransferQueryDto transferQueryDto);

}

