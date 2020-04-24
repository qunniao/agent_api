package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 后台菜单
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    Menu queryInfo(@Param("mid") Integer mid);
}
