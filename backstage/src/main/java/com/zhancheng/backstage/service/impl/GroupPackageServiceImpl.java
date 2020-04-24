package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.GroupPackageService;
import com.zhancheng.backstage.service.GroupProductService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.dao.GroupProductMapper;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.GroupProduct;
import com.zhancheng.core.vo.GroupPackageVo;
import com.zhancheng.core.vo.GroupProductVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.GroupPackageMapper;
import com.zhancheng.core.entity.GroupPackage;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品组合套餐
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */

@Service
public class GroupPackageServiceImpl extends ServiceImpl<GroupPackageMapper, GroupPackage> implements GroupPackageService {

    @Resource
    private GroupProductService groupProductService;

    @Resource
    private GroupProductMapper groupProductMapper;



    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(GroupPackage groupPackage) {

        groupPackage.insert();

        Integer gid = groupPackage.getGid();
        // 套餐产品列表
        List<GroupProduct> groupProductList = groupPackage.getGroupProductList();
        if (ObjectUtil.isEmpty(groupProductList)) {
            throw new MyException(CodeMsg.GROUP_PRODUCT_IS_NULL);
        }

        for (GroupProduct groupProduct : groupProductList) {
            groupProduct.setGid(gid);
        }

        return  groupProductService.saveBatch(groupProductList, 5);
    }

    @Override
    public Boolean updateInfo(GroupPackage groupPackage){

        groupPackage.updateById();

        Integer gid = groupPackage.getGid();

        // 套餐产品列表
        List<GroupProduct> groupProductList = groupPackage.getGroupProductList();
        if (ObjectUtil.isNotEmpty(groupProductList)) {
            throw new MyException(CodeMsg.GROUP_PRODUCT_IS_NULL);
        }

        groupProductMapper.deleteByGid(gid);

        for (GroupProduct groupProduct : groupProductList) {
            groupProduct.setGid(gid);
        }

        return  groupProductService.saveBatch(groupProductList, 5);
    }

    @Override
    public List<GroupPackage> queryList(Integer levelId) {

        AgentLevel agentLevel = agentLevelMapper.selectById(levelId);

        if (ObjectUtil.isNull(agentLevel)) {
            throw new MyException(CodeMsg.AGENT_LEVEL_IS_NULL);
        }

        if (agentLevel.getStockType() == 2){
            String gids = agentLevel.getGids();
            return baseMapper.queryListByGids(gids);
        }

        return null;
    }

    @Override
    public GroupPackageVo queryInfo(Integer gid) {

        GroupPackageVo groupPackageVo = baseMapper.queryInfo(gid);

        if (ObjectUtil.isNull(groupPackageVo)){
            throw new MyException(CodeMsg.GROUP_PACKAGE_IS_NULL);
        }

        // 查询套餐组合产品
        List<GroupProductVo> groupProductVoList = groupProductMapper.queryListByGid(gid);

        groupPackageVo.setGroupProductVoList(groupProductVoList);

        return groupPackageVo;
    }
}