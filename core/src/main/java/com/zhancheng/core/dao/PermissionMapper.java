package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 后台功能权限
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    Permission queryInfo(@Param("pid") Integer pid);
}
