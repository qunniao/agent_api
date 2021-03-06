package com.zhancheng.retail.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.UserAddressMapper;
import com.zhancheng.core.entity.UserAddress;
import com.zhancheng.retail.service.UserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户地址
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Override
    public IPage<UserAddress> selectPage(PageDto<UserAddress> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<UserAddress>().eq("is_deleted", 0));
    }

    @Override
    public UserAddress info(Integer id) {

        return baseMapper.selectById(id);
    }

    @Override
    public Boolean insert(UserAddress userAddress) {

        if (userAddress.getIsDefaultAddress()){
            UserAddress userAddress1 = queryDefault(userAddress.getUid());
            if (ObjectUtil.isNotNull(userAddress1)){
                userAddress1.setIsDefaultAddress(Boolean.FALSE);
                userAddress1.updateById();
            }
        }

        return  baseMapper.insert(userAddress) > 0;
    }

    @Override
    public Boolean update(UserAddress userAddress) {

        if (userAddress.getIsDefaultAddress()){
            UserAddress userAddress1 = queryDefault(userAddress.getUid());
            if (ObjectUtil.isNotNull(userAddress1)){
                userAddress1.setIsDefaultAddress(Boolean.FALSE);
                userAddress1.updateById();
            }
        }

        return baseMapper.updateById(userAddress) > 0;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public UserAddress queryDefault(Integer uid) {

        return baseMapper.selectOne(new QueryWrapper<UserAddress>()
                .eq("uid", uid).eq("is_default_address", 1).eq("is_deleted", 0));
    }

}