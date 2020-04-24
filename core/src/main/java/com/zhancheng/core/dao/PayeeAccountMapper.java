package com.zhancheng.core.dao;

import com.zhancheng.core.entity.PayeeAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 总部账户
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 18:02:48
 */
@Repository
public interface PayeeAccountMapper extends BaseMapper<PayeeAccount> {
	
}
