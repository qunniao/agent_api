package com.zhancheng.core.dao;

import com.zhancheng.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:40:08
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
