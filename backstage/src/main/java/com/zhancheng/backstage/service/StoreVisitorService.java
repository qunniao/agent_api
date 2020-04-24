package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.StoreVisitorQueryDto;
import com.zhancheng.core.entity.StoreVisitor;
import com.zhancheng.core.vo.StoreVisitorVo;

/**
 * 店铺访客记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */
public interface StoreVisitorService extends IService<StoreVisitor> {

   IPage<StoreVisitorVo> selectPage(PageDto<StoreVisitor> pageDto, StoreVisitorQueryDto storeVisitorQueryDto);

   IPage<StoreVisitorVo> info(Integer id);
}

