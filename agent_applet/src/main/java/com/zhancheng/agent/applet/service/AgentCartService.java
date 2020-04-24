package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.entity.AgentCart;
import com.zhancheng.core.vo.OrderCartListVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 购物车表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-04 18:05:12
 */
public interface AgentCartService extends IService<AgentCart> {

    List<OrderCartListVo> queryList(Integer agentId);

    Integer submitCart(AgentOrderDto agentOrderDto);

    Boolean insertOrUpdate(@RequestBody AgentCart agentCart);

    /**
     * 添加或减少商品数量
     * @param orderCartDto 购物车数据
     * @return
     */
    Boolean changeProductNum(OrderCartDto orderCartDto);

    List<OrderCartListVo> queryInfo(List<Integer> cIds);

}

