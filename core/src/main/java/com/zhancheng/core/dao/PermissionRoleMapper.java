package com.zhancheng.core.dao;

import com.zhancheng.core.entity.PermissionRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台功能权限分配
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Repository
public interface PermissionRoleMapper extends BaseMapper<PermissionRole> {

    List<PermissionRole> queryList(@Param("rid") Integer rid);
}
