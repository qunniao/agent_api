package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.AgentWalletMapper;
import com.zhancheng.core.dao.TransferMapper;
import com.zhancheng.core.dto.TransferDto;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.AgentWallet;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.TransferListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferServiceImpl
 * @date 2019/11/8 15:40
 */
@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements TransferService{

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private AgentWalletMapper agentWalletMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean agentTransfer(TransferDto transferDto) {

        // 打款金额
        BigDecimal amount = transferDto.getAmount();

        // 查询打款代理
        Agent remitAgent = agentMapper.selectById(transferDto.getAgentId());

        // 查询收款代理
        Agent collectionAgent = agentMapper.selectOne(new QueryWrapper<Agent>().eq("true_name", transferDto.getTrueName())
                .eq("phone", transferDto.getPhone()));

        // 判断两个代理是否存在
        if (ObjectUtil.isNull(remitAgent) || ObjectUtil.isNull(collectionAgent)) {
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        }

        // 判断两个人是否都是自己
        if (remitAgent.getId().equals(collectionAgent.getId())) {
            throw new MyException(CodeMsg.TRANSFER_ERROR);
        }

        // 查询打款代理钱包
        AgentWallet remitWallet = agentWalletMapper.queryByAgentId(remitAgent.getId());
        // 查询收款代理钱包
        AgentWallet collectionWallet = agentWalletMapper.queryByAgentId(collectionAgent.getId());

        if (ObjectUtil.isNull(remitWallet) || ObjectUtil.isNull(collectionWallet)) {
            throw new MyException(CodeMsg.WALLET_IS_NULL);
        }

        // 打款代理钱包余额
        BigDecimal remitBalance = remitWallet.getIncomeBalance();
        // 收款代理钱包余额
        BigDecimal collectionBalance = collectionWallet.getIncomeBalance();

        if (remitBalance.compareTo(amount) < 0) {
            throw new MyException(CodeMsg.INSUFFICIENT_BALANCE);
        }

        // 分别修改钱包金额
        remitWallet.setIncomeBalance(remitBalance.subtract(amount));

        collectionWallet.setIncomeBalance(collectionBalance.add(amount));

        remitWallet.updateById();
        collectionWallet.updateById();

        Transfer transfer = new Transfer();
        //转账说明
        String description = remitAgent.getTrueName() + "向" + collectionAgent.getTrueName() + "转账" + amount;

        transfer.setAgentId(remitAgent.getId()).setPayeeId(collectionAgent.getId()).setPhone(transferDto.getPhone())
                .setTrueName(transferDto.getTrueName()).setAmount(amount).setDescription(description);

        return transfer.insert();
    }

    @Override
    public IPage<TransferListVo> selectPage(PageDto pageDto, TransferQueryDto transferQueryDto) {

        return baseMapper.queryPage(pageDto.getPage(), transferQueryDto);
    }
}
