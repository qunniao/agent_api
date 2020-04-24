package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AgentHistoryService;
import com.zhancheng.backstage.service.AgentService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dto.AgentQueryDto;
import com.zhancheng.core.dto.FranchiseeDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.vo.AgentInfoVo;
import com.zhancheng.core.vo.AgentListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */

@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements AgentService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public IPage<AgentListVo> queryPage(PageDto pageDto, AgentQueryDto agentQueryDto) {

        return baseMapper.queryPage(pageDto.getPage(), agentQueryDto);
    }

    @Override
    public IPage<AgentInfoVo> queryInfoList(PageDto pageDto, AgentQueryDto agentQueryDto) {

        return baseMapper.queryInfoList(pageDto.getPage(), agentQueryDto);
    }

    @Override
    public Agent queryInfo(Integer id) {

        return baseMapper.queryById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registerFranchisee(FranchiseeDto franchiseeDto) {

        Agent agent = new Agent();

        BeanUtil.copyProperties(franchiseeDto, agent);

        // 查询代理手机号是否存在
        Agent agentInfo = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", franchiseeDto.getPhone()));

        if (ObjectUtil.isNotNull(agentInfo)){
            throw new MyException(CodeMsg.AGENT_NOT_NULL);
        }

        agent.setInviter(0).setPassword(SecureUtil.md5(franchiseeDto.getPassword()))
                .setRegion(franchiseeDto.getRegion()).setLevelId(9).setReviewed(1);

        agent.insert();

        Integer agentId = agent.getId();

        // 添加审核记录
        AgentRegisterReview agentRegisterReview = new AgentRegisterReview();
        agentRegisterReview.setAgentId(agentId).setType(2).setInviter(0)
                .setStatus(3);
        agentRegisterReview.insert();

        // 添加店铺
        Store store = new Store();
        String storeName;

        if (StrUtil.isNotBlank(franchiseeDto.getStoreName())){
            storeName = franchiseeDto.getStoreName();
        }else {
            storeName = franchiseeDto.getTrueName() + "的店铺";
        }

        store.setAgentId(agentId).setStoreName(storeName);
        store.insert();

        // 添加钱包
        AgentWallet agentWallet = new AgentWallet();
        agentWallet.setAgentId(agentId);

        return agentWallet.insert();

    }

    @Override
    public Boolean updateFranchisee(Agent agent) {

        String inviterPhone = agent.getInviterPhone();

        if (StrUtil.isNotBlank(inviterPhone)) {
            Agent phone = baseMapper.selectOne(new QueryWrapper<Agent>().eq("phone", inviterPhone));
            if (ObjectUtil.isNull(phone)) {
                throw new MyException(CodeMsg.PHONE_NOT_EXISTED);
            }
            agent.setInviter(phone.getId());
        }

        return agent.updateById();
    }

}