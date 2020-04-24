package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.StoreVisitorService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.StoreVisitorMapper;
import com.zhancheng.core.dto.StoreVisitorQueryDto;
import com.zhancheng.core.entity.StoreVisitor;
import com.zhancheng.core.vo.StoreVisitorVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 店铺访客记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */

@Service
public class StoreVisitorServiceImpl extends ServiceImpl<StoreVisitorMapper, StoreVisitor> implements StoreVisitorService {

    @Override
    public IPage<StoreVisitorVo> selectPage(PageDto<StoreVisitor> pageDto, StoreVisitorQueryDto storeVisitorQueryDto) {
        return baseMapper.queryPage(pageDto.getPage(), storeVisitorQueryDto);
    }

    @Override
    public IPage<StoreVisitorVo> info(Integer id) {
        return null;
    }
}