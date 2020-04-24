package com.zhancheng.core.dao;

import com.zhancheng.core.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户地址
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */
@Repository
public interface UserAddressMapper extends BaseMapper<UserAddress> {
	
}
