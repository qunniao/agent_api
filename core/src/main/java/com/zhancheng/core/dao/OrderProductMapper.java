package com.zhancheng.core.dao;

import com.zhancheng.core.entity.OrderProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.OrderProductBackVo;
import com.zhancheng.core.vo.OrderProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单商品表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Repository
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    List<OrderProductVo> queryInfo(@Param("orderNumber") String orderNumber);

    List<OrderProductBackVo> queryBackInfo(@Param("orderNumber") String orderNumber);

    Integer countOrderProduct(String orderNumber);
}
