package com.zhancheng.agent.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.AdminService;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.entity.Admin;
import org.springframework.stereotype.Service;


/**
 * 管理员
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


}