package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.ParamGroup;
import com.zhancheng.core.vo.ParamGroupListVo;

import java.util.List;
import java.util.Map;

/**
 * 产品属性组
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 17:06:38
 */
public interface ParamGroupService extends IService<ParamGroup> {

    /**
     * 分页查询产品属性组列表
     * @param pageDto 分页参数
     * @param keyWord 关键字
     * @param sortType 排序类型
     * @return IPage<ParamGroup>
     */
    IPage<ParamGroupListVo> selectPage(PageDto<ParamGroup> pageDto, String keyWord, String sortType);

    /**
     * 查询产品属性组列表
     * @return List
     */
    List<Map<String, String>> selectList();

    /**
     * 查询产品属性组详情
     * @param pgid 主键Id
     * @return
     */
    ParamGroup info(Integer pgid);

    /**
     * 添加产品属性组
     * @param paramGroup 产品属性组数据
     * @return Boolean
     */
    Boolean insert(ParamGroup paramGroup);

    /**
     * 修改产品属性组
     * @param paramGroup 产品属性组数据
     * @return Boolean
     */
    Boolean update(ParamGroup paramGroup);

    /**
     * 批量删除产品属性组
     * @param pgids 产品属性组id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> pgids);
}

