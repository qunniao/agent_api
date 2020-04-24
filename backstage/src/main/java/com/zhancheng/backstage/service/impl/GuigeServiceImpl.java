package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.GuigeService;
import com.zhancheng.core.dao.GuigeMapper;
import com.zhancheng.core.entity.Guige;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-19 14:47:52
 */

@Service
public class GuigeServiceImpl extends ServiceImpl<GuigeMapper, Guige> implements GuigeService {

    @Override
    public List<Guige> selectList() {
        return baseMapper.selectList(new QueryWrapper<Guige>()
                .eq("is_deleted", 0)
                .orderByDesc("sort"));
    }

    @Override
    public Guige info(Integer sgid) {

        return baseMapper.selectById(sgid);
    }

    @Override
    public Boolean insert(Guige guige) {
        return baseMapper.insert(guige) > 0;
    }

    @Override
    public Boolean update(Guige guige) {
        return baseMapper.updateById(guige) > 0;
    }

    @Override
    public Boolean delete(List<Integer> sgids) {
        return baseMapper.deleteBatchIds(sgids) > 0;
    }

}