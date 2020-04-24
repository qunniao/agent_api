package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.WalletHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.WalletHistoryListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 钱包收支明细记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Repository
public interface WalletHistoryMapper extends BaseMapper<WalletHistory> {

   IPage<WalletHistoryListVO> queryPage(Page page, @Param("query") WalletHistoryQueryDTO walletHistoryQueryDTO);
}
