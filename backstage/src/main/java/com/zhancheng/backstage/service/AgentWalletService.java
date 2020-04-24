package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.AgentWalletDTO;
import com.zhancheng.core.entity.AgentWallet;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 代理人钱包
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 13:53:37
 */
public interface AgentWalletService extends IService<AgentWallet> {

    Boolean adjust(@RequestBody AgentWalletDTO agentWalletDTO);
}

