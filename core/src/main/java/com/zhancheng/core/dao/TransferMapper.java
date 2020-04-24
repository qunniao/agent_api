package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Transfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.TransferListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 转账记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 14:40:29
 */
@Repository
public interface TransferMapper extends BaseMapper<Transfer> {

    IPage<TransferListVo> queryPage(Page page, @Param("query") TransferQueryDto transferQueryDto);
}
