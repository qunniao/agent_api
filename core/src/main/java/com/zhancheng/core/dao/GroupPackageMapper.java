package com.zhancheng.core.dao;

import com.zhancheng.core.entity.GroupPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.GroupPackageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品组合套餐
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
@Repository
public interface GroupPackageMapper extends BaseMapper<GroupPackage> {

   List<GroupPackage> queryListByGids(@Param("gids") String gids);

    /**
     * 查询套餐组合详情
     * @param gid 查询套餐组合id
     * @return
     */
   GroupPackageVo queryInfo(@Param("gid") Integer gid);
}
