package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AgentRegisterReviewService;
import com.zhancheng.backstage.service.StoreService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.AgentRegisterReviewMapper;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.dto.AgentReviewsQueryDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.AgentRegisterReview;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.entity.Store;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.vo.AgentReviewListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 代理加入审核
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 11:07:38
 */

@Service
public class AgentRegisterReviewServiceImpl extends ServiceImpl<AgentRegisterReviewMapper, AgentRegisterReview> implements AgentRegisterReviewService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private StoreService storeService;

    @Resource
    private AgentMapper agentMapper;

    @Override
    public IPage<AgentReviewListVo> queryPage(PageDto<AgentRegisterReview> pageDto, AgentReviewsQueryDto agentReviewsQueryDto) {
        return baseMapper.queryPage(pageDto.getPage(), agentReviewsQueryDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean ratifyVoucher(Integer id) {

        AgentRegisterReview agentRegisterReview = baseMapper.selectById(id);

        if (ObjectUtil.isNull(agentRegisterReview)){
            throw new MyException(CodeMsg.AGENT_REVIEW_IS_NULL);
        }

        Integer orderId = agentRegisterReview.getOrderId();

        if (ObjectUtil.isNotNull(orderId)){
            OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
            orderInfo.setOrderState(OrderStateEnum.AWAIT_SEND_OUT.getState());
            orderInfo.setVoucherState(OrderStateEnum.RATIFY_VOUCHER.getState());
        }

        // 更新状态
        agentRegisterReview.setStatus(3);
        agentRegisterReview.updateById();

        Agent agent = agentMapper.selectById(agentRegisterReview.getAgentId());

        if (ObjectUtil.isNull(agent)){
            throw new MyException(CodeMsg.AGENT_REVIEW_IS_NULL);
        }
        // 修改代理审核状态
        agent.setReviewed(1);
        agent.updateById();

        Store store = new Store();

        store.setAgentId(agent.getId()).setStoreName(agent.getNickname()+ "的店铺")
                .setStoreCover(agent.getCover());

        return storeService.insertStore(store);
    }

    @Override
    public Boolean rejectVoucher(Integer id, String refuseReason) {

        AgentRegisterReview agentRegisterReview = baseMapper.selectById(id);

        if (ObjectUtil.isNull(agentRegisterReview)){
            throw new MyException(CodeMsg.AGENT_REVIEW_IS_NULL);
        }

        Integer orderId = agentRegisterReview.getOrderId();

        // 订单改为已驳回
        if (ObjectUtil.isNotNull(orderId)){
            OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
            orderInfo.setVoucherState(OrderStateEnum.REJECT_VOUCHER.getState());
        }

        agentRegisterReview.setStatus(4);
        agentRegisterReview.setRefuseReason(refuseReason);

        return agentRegisterReview.updateById();
    }
}