package com.zhancheng.retail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentOrderDto;
import com.zhancheng.core.dto.OrderCartDto;
import com.zhancheng.core.entity.OrderCart;
import com.zhancheng.core.vo.OrderCartListVo;

import java.util.List;

/**
 * 购物车表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
public interface OrderCartService extends IService<OrderCart> {

    /**
     * 分页查询购物车表列表
     * @param pageDto 分页参数
     * @return IPage<OrderCart>
     */
    IPage<OrderCartListVo> selectPage(PageDto<OrderCart> pageDto, Integer pid);

    /**
     * 查询购物车表详情
     * @param cIds 主键Id
     * @return
     */
    List<OrderCartListVo> info(List<Integer> cIds);

    /**
     * 添加购物车表
     * @param orderCart 购物车表数据
     * @return Boolean
     */
    Boolean insertOrUpdate(OrderCart orderCart);

    /**
     * 添加或减少商品数量
     * @param orderCartDto 购物车数据
     * @return
     */
    Boolean changeProductNum(OrderCartDto orderCartDto);

    /**
     * 批量删除购物车表
     * @param cids 购物车表id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> cids);

    /**
     * 提交购物车
     * @param agentOrderDto 购物车数据
     * @return 订单id
     */
    Integer submitCart(AgentOrderDto agentOrderDto);
}

