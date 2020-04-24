package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Remit;



/**
 * 打款审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */
public interface RemitService extends IService<Remit> {

    IPage<Remit> queryPage(PageDto<Remit> pageDto);

    Remit queryInfo(Integer id);

    /**
     *     通过审核
     */
    Boolean ratifyVoucher(Integer id, Integer auditorType);

    /**
     * 驳回审核
     * @param id
     * @param refuseReason
     * @param auditorType
     * @return
     */
    Boolean rejectVoucher(Integer id, String refuseReason, Integer auditorType);
}

