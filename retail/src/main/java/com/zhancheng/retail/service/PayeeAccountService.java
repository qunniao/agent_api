package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.PayeeAccount;

import java.util.List;

/**
 * 总部账户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 18:02:48
 */
public interface PayeeAccountService extends IService<PayeeAccount> {

    List<PayeeAccount> queryList(Integer agentId);
}

