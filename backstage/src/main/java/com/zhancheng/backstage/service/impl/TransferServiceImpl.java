package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.TransferMapper;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.TransferListVo;
import org.springframework.stereotype.Service;

/**
 * 转账记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 14:40:29
 */

@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements TransferService {

    @Override
    public IPage<TransferListVo> selectPage(PageDto pageDto, TransferQueryDto transferQueryDto) {

        return baseMapper.queryPage(pageDto.getPage(), transferQueryDto);
    }
}