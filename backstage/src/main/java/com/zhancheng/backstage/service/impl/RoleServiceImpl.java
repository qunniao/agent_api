package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.RoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.RoleMapper;
import com.zhancheng.core.entity.Role;

/**
 * 管理员角色
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}