package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.BackOrderQueryDto;
import com.zhancheng.core.dto.OrderQueryDto;
import com.zhancheng.core.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.OrderBackListVo;
import com.zhancheng.core.vo.OrderInfoVo;
import com.zhancheng.core.vo.OrderListVo;
import com.zhancheng.core.vo.RetailOrderInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

   IPage<OrderInfo> queryPage(Page page, @Param("query") OrderQueryDto orderQueryDto);

   IPage<OrderBackListVo> queryBackPage(Page page, @Param("query") BackOrderQueryDto backOrderQueryDto);

   RetailOrderInfoVo queryInfo(Integer oid);

   OrderInfoVo queryDetails(@Param("oid")Integer oid, @Param("orderNumber") String orderNumber);

}
