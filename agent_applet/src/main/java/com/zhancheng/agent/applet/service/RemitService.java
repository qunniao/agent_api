package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.Remit;

/**
 * 打款审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */
public interface RemitService extends IService<Remit> {

    Boolean insert(Remit remit);

}

