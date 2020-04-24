package com.zhancheng.agent.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.GroupPackage;
import com.zhancheng.core.vo.GroupPackageVo;

import java.util.List;

/**
 * 产品组合套餐
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
public interface GroupPackageService extends IService<GroupPackage> {

    List<GroupPackage> queryList(Integer levelId);

    GroupPackageVo queryInfo(Integer gid);
}

