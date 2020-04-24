package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Remit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 打款审核
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-30 11:29:07
 */
@Repository
public interface RemitMapper extends BaseMapper<Remit> {
	
}
