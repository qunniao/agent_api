package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.agent.applet.service.GroupPackageService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.dao.GroupPackageMapper;
import com.zhancheng.core.dao.GroupProductMapper;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.GroupPackage;
import com.zhancheng.core.entity.GroupProduct;
import com.zhancheng.core.vo.GroupPackageVo;
import com.zhancheng.core.vo.GroupProductVo;
import org.springframework.stereotype.Service;
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
    private GroupProductMapper groupProductMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Override
    public List<GroupPackage> queryList(Integer levelId) {

        AgentLevel agentLevel = agentLevelMapper.selectById(levelId);

        if (ObjectUtil.isNull(agentLevel)) {
            throw new MyException(CodeMsg.AGENT_LEVEL_IS_NULL);
        }

        if (agentLevel.getStockType() == 2){
            String gids = agentLevel.getGids();
            System.err.println(gids);
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