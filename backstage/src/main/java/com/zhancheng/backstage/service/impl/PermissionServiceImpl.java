package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.PermissionService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.PermissionMapper;
import com.zhancheng.core.entity.Permission;

/**
 * 后台功能权限
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}