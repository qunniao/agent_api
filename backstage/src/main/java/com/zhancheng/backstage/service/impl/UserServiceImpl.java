package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:40:08
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}