package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AdminDto;
import com.zhancheng.core.entity.Admin;

import java.util.List;
import java.util.Map;

/**
 * 管理员
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
public interface AdminService extends IService<Admin> {

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @param code 手机号验证码
     * @return Map<String, Object>
     */
    Map<String, Object> phoneLogin(String phone, String password, String code);

    /**
     * 分页查询管理员列表
     * @param pageDto 分页参数
     * @return IPage<Admin>
     */
    IPage<Admin> selectPage(PageDto<Admin> pageDto);

    /**
     * 查询管理员详情
     * @param uid 主键Id
     * @return
     */
    Admin info(Integer uid);

    /**
     * 添加管理员
     * @param adminDto 管理员数据
     * @return Boolean
     */
    Boolean insert(AdminDto adminDto);

    /**
     * 修改管理员
     * @param admin 管理员数据
     * @return Boolean
     */
    Boolean update(Admin admin);

    /**
     * 批量删除管理员
     * @param uids 管理员id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> uids);
}

