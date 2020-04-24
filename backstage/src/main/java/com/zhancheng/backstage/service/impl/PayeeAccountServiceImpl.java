package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.PayeeAccountService;
import com.zhancheng.core.dao.PayeeAccountMapper;
import com.zhancheng.core.entity.PayeeAccount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 总部账户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 18:02:48
 */

@Service
public class PayeeAccountServiceImpl extends ServiceImpl<PayeeAccountMapper, PayeeAccount> implements PayeeAccountService {

    @Override
    public List<PayeeAccount> queryList() {
        return baseMapper.selectList(new QueryWrapper<>());
    }
}