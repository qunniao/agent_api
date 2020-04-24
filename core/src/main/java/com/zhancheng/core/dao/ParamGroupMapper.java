package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.ParamGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ParamGroupListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品属性组
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 17:06:38
 */
@Repository
public interface ParamGroupMapper extends BaseMapper<ParamGroup> {

    /**
     * 分页查询属性组列表
     * @param page 分页信息
     * @param keyWord 关键字
     * @param sortType 排序类型
     * @return IPage<ParamGroupListVo>
     */
    IPage<ParamGroupListVo> queryPage(Page page,@Param("keyWord") String keyWord, @Param("sortType") String sortType);

    /**
     * 查询属性组列表
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> queryList();
}
