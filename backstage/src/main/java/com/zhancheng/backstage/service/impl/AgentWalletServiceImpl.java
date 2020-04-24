package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.AgentWalletService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.MyAspect;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dto.AgentWalletDTO;
import com.zhancheng.core.entity.Admin;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.util.OrderNumUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AgentWalletMapper;
import com.zhancheng.core.entity.AgentWallet;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 代理人钱包
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 13:53:37
 */

@Service
public class AgentWalletServiceImpl extends ServiceImpl<AgentWalletMapper, AgentWallet> implements AgentWalletService {

    @Resource
    private AgentWalletMapper agentWalletMapper;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Boolean adjust(AgentWalletDTO agentWalletDTO) {

        StringBuffer tradeDesc = new StringBuffer();

        Integer agentId = agentWalletDTO.getAgentId();

        Integer adminId = agentWalletDTO.getAdminId();

        Agent agent = agentMapper.selectById(agentId);

        if (ObjectUtil.isNull(agent)) {
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        }

        Admin admin = adminMapper.selectById(adminId);
        if (ObjectUtil.isNull(admin)) {
            throw new MyException(CodeMsg.ADMIN_IS_NULL);
        }

        AgentWallet agentWallet = agentWalletMapper.selectOne(new QueryWrapper<AgentWallet>()
                .eq("agent_id",agentId ));

        if (ObjectUtil.isNull(agentWallet)) {
            throw new MyException(CodeMsg.WALLET_IS_NULL);
        }

        tradeDesc.append(admin.getNickname()).append("给").append(agent.getNickname());
        Integer changeType = agentWalletDTO.getChangeType();
        // 金额
        BigDecimal amount = agentWalletDTO.getAmount();

        // 用户余额
        BigDecimal incomeBalance = agentWallet.getIncomeBalance();
        if (ObjectUtil.isNotNull(changeType) && changeType == 1) {
            incomeBalance = incomeBalance.add(amount);
            agentWallet.setIncomeBalance(incomeBalance);
            tradeDesc.append("增加");
        }else {
            if (amount.compareTo(incomeBalance) > 0) {
                throw new MyException(CodeMsg.AMOUNT_ERROR);
            }
            incomeBalance = incomeBalance.subtract(amount);
            agentWallet.setIncomeBalance(incomeBalance);
            tradeDesc.append("减少");
        }

        tradeDesc.append(amount);

        agentWallet.updateById();

        String tradeNo = OrderNumUtil.generateTradeNo();
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setAgentId(agentId).setTradeType(changeType).setAmount(amount).setTradeNo(tradeNo)
                .setBalance(agentWallet.getIncomeBalance()).setTradeDesc(tradeDesc.toString());

        return walletHistory.insert();
    }
}