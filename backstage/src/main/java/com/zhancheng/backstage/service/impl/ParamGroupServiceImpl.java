package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ParamGroupService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.ParamGroupMapper;
import com.zhancheng.core.entity.ParamGroup;
import com.zhancheng.core.vo.ParamGroupListVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品属性组
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 17:06:38
 */

@Service
public class ParamGroupServiceImpl extends ServiceImpl<ParamGroupMapper, ParamGroup> implements ParamGroupService {

    @Override
    public IPage<ParamGroupListVo> selectPage(PageDto<ParamGroup> pageDto, String keyWord, String sortType) {
        return baseMapper.queryPage(pageDto.getPage(), keyWord, sortType);
    }

    @Override
    public List<Map<String, String>> selectList() {
        return baseMapper.queryList();
    }

    @Override
    public ParamGroup info(Integer pgid) {

        return baseMapper.selectById(pgid);
    }

    @Override
    public Boolean insert(ParamGroup paramGroup) {
        return  baseMapper.insert(paramGroup) > 0;
    }

    @Override
    public Boolean update(ParamGroup paramGroup) {
        return baseMapper.updateById(paramGroup) > 0;
    }

    @Override
    public Boolean delete(List<Integer> pgids) {
        return baseMapper.deleteBatchIds(pgids) > 0;
    }

}