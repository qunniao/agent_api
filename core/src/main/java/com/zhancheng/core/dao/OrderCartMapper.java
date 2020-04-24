package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.OrderCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.OrderCartListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 14:38:29
 */
@Repository
public interface OrderCartMapper extends BaseMapper<OrderCart> {

    IPage<OrderCartListVo> queryPage(Page page, Integer uid);

    List<OrderCartListVo> queryInfo(@Param("cIds") List<Integer> cIds);

}
