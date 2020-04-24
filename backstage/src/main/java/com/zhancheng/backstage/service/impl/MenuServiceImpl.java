package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.MenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.MenuMapper;
import com.zhancheng.core.entity.Menu;

/**
 * 后台菜单
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-26 16:19:38
 */

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}