package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员角色
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Role queryInfo(@Param("rid") Integer rid);
}
