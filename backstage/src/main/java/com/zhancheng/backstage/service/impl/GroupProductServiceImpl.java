package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.GroupProductService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.GroupProductMapper;
import com.zhancheng.core.entity.GroupProduct;

/**
 * 组合套餐的产品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */

@Service
public class GroupProductServiceImpl extends ServiceImpl<GroupProductMapper, GroupProduct> implements GroupProductService {

}