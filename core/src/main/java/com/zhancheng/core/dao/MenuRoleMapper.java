package com.zhancheng.core.dao;

import com.zhancheng.core.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台菜单权限
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    List<MenuRole> queryList(@Param("rid") Integer rid);
}
