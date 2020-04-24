package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Guige;

import java.util.List;

/**
 * 规格
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-19 14:47:52
 */
public interface GuigeService extends IService<Guige> {

    /**
     * 查询规格列表
     * @return List<Guige>
     */
    List<Guige> selectList();

    /**
     * 查询规格详情
     * @param sgid 主键Id
     * @return
     */
    Guige info(Integer sgid);

    /**
     * 添加规格
     * @param guige 规格数据
     * @return Boolean
     */
    Boolean insert(Guige guige);

    /**
     * 修改规格
     * @param guige 规格数据
     * @return Boolean
     */
    Boolean update(Guige guige);

    /**
     * 批量删除规格
     * @param sgids 规格id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> sgids);
}

