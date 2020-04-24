package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AdminService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dto.AdminDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.AgentLevel;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.entity.Admin;
import sun.management.resources.agent;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Map<String, Object> phoneLogin(String phone, String password, String code) {

        Map<String, Object> map = new HashMap<>(2);

        // 获取验证码
        String phoneCode = redisTemplate.getKey(phone);

        // 验证码已失效
        if (StrUtil.isBlank(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_IS_NULL);
        }

        if (!code.equals(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_ERROR);
        }

        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("phone", phone).eq("pwd", SecureUtil.md5(password)));

        if (ObjectUtil.isNull(admin)) {
            throw new MyException(CodeMsg.PASSWORD_ERROR);
        }

        map.put("admin", admin);

        return map;
    }

    @Override
    public IPage<Admin> selectPage(PageDto<Admin> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<Admin>().eq("is_deleted", 0));
    }

    @Override
    public Admin info(Integer uid) {

        return baseMapper.selectById(uid);
    }

    @Override
    public Boolean insert(AdminDto adminDto) {

        Admin admin = new Admin();

        BeanUtil.copyProperties(adminDto, admin);

        Admin adminInfo = baseMapper.selectOne(new QueryWrapper<Admin>().eq("phone", admin.getPhone()));

        if (ObjectUtil.isNotNull(adminInfo)) {
            throw new MyException(CodeMsg.PHONE_IS_EXISTED);
        }

        String password = SecureUtil.md5(adminDto.getPwd());

        admin.setPwd(password);

        return admin.insert();
    }

    @Override
    public Boolean update(Admin admin) {
        return baseMapper.updateById(admin) > 0;
    }

    @Override
    public Boolean delete(List<Integer> uids) {
        return baseMapper.deleteBatchIds(uids) > 0;
    }

}