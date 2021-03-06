package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.WalletHistoryService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.vo.WalletHistoryListVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.WalletHistoryMapper;
import com.zhancheng.core.entity.WalletHistory;

/**
 * 钱包收支明细记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */

@Service
public class WalletHistoryServiceImpl extends ServiceImpl<WalletHistoryMapper, WalletHistory> implements WalletHistoryService {

    @Override
    public IPage<WalletHistoryListVO> queryPage(PageDto<WalletHistory> pageDto, WalletHistoryQueryDTO walletHistoryQueryDTO) {
        return baseMapper.queryPage(pageDto.getPage(), walletHistoryQueryDTO);
    }
}