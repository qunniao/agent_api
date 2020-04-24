package com.zhancheng.agent.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.agent.applet.service.AgentCartService;
import com.zhancheng.agent.applet.service.AgentService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.AgentDto;
import com.zhancheng.core.dto.FranchiseeDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 17:54:39
 */

@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements AgentService {

    @Resource
    private WxUtil wxUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentCartService agentCartService;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Agent queryInfo(Integer id) {
        return baseMapper.queryInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Agent register(AgentDto agentDto){

        Agent agentInfo = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", agentDto.getPhone()));
        if (ObjectUtil.isNotNull(agentInfo)){
            throw new MyException(CodeMsg.AGENT_NOT_NULL);
        }

        Agent agent = new Agent();
        BeanUtil.copyProperties(agentDto, agent);

        // 获取accessToken
        JSONObject json = wxUtil.getAccessToken(agentDto.getCode());

        String openId = json.getStr("openid");

        Agent agentDetails = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("openid", openId));
        if (ObjectUtil.isNotNull(agentDetails)){
            throw new MyException(CodeMsg.AGENT_NOT_NULL);
        }
        System.err.println(agent);
        addAgentInfo(json, agent);
        System.err.println(agent);

        agent.setSuperior(agentDto.getInviter()).setPassword(SecureUtil.md5(agentDto.getPassword()));

        // 绑定用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("openid", openId));
        if (ObjectUtil.isNotNull(user)) {
            agent.setUserId(user.getUid());
        }

        agent.insert();
        Integer agentId = agent.getId();

        AgentLevel agentLevel = agentLevelMapper.selectOne(new QueryWrapper<AgentLevel>().eq("id", agentDto.getLevelId()));

        if (ObjectUtil.isNull(agentLevel)){
            throw new MyException(CodeMsg.AGENT_LEVEL_IS_NULL);
        }

        // 添加代理注册审核记录
        AgentRegisterReview agentRegisterReview = new AgentRegisterReview();
        if (agentLevel.getIsTask() == 0){
            agentRegisterReview.setApplyInfo("无任务");
        }else {
            agentRegisterReview.setApplyInfo("首次进货:");
        }

        // 添加购物车
        List<AgentCart> agentCartList = agentDto.getAgentCartList();

        if (ObjectUtil.isNotEmpty(agentCartList)){
            for (AgentCart agentCart : agentCartList) {
                agentCart.setAgentId(agentId);
            }
            agentCartService.saveBatch(agentCartList);
        }

        agentRegisterReview.setAgentId(agent.getId()).setType(1)
                .setInviter(agent.getInviter()).setStatus(1);

        agentRegisterReview.insert();

        AgentWallet agentWallet = new AgentWallet();
        agentWallet.setAgentId(agentId);
        agentWallet.insert();

        return agent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Agent registerFranchisee(FranchiseeDto franchiseeDto) {

        Agent agent = new Agent();

        BeanUtil.copyProperties(franchiseeDto, agent);

        String adminPhone = franchiseeDto.getAdminPhone();
        Admin admin = new Admin();
        if (StrUtil.isNotBlank(adminPhone)) {
            // 查询管理员手机号是否存在
            admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                    .eq("phone", franchiseeDto.getAdminPhone()).eq("role_id",3));

            if (ObjectUtil.isNull(admin)) {
                throw new MyException(CodeMsg.ADMIN_IS_NULL);
            }
        }

        // 获取accessToken
        JSONObject json = wxUtil.getAccessToken(franchiseeDto.getCode());

        // 查询openId是否存在
        Agent agentDetails = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("openid", json.getStr("openid")));

        // 查询手机号是否存在
        Agent agentInfo = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", franchiseeDto.getPhone()));

        if (ObjectUtil.isNotNull(agentDetails) || ObjectUtil.isNotNull(agentInfo) ){
            throw new MyException(CodeMsg.AGENT_NOT_NULL);
        }

        String storeCover = json.getStr("headimgurl");

        // 完善加盟商信息
        addAgentInfo(json, agent);
        agent.setInviter(franchiseeDto.getInviter()).setPassword(SecureUtil.md5(franchiseeDto.getPassword()))
                .setRegion(franchiseeDto.getRegion()).setLevelId(9).setReviewed(0);

        if (ObjectUtil.isNotNull(admin)) {
            agent.setAdminId(admin.getUid());
        }
        agent.insert();

        Integer agentId = agent.getId();

        // 添加审核记录
        AgentRegisterReview agentRegisterReview = new AgentRegisterReview();
        agentRegisterReview.setAgentId(agentId).setType(2).setInviter(franchiseeDto.getInviter())
            .setStatus(3);
        agentRegisterReview.insert();

        String storeName;

        if (StrUtil.isNotBlank(franchiseeDto.getStoreName())){
            storeName = franchiseeDto.getStoreName();
        }else {
            storeName = franchiseeDto.getTrueName() + "的店铺";
        }
        // 添加店铺
        Store store = new Store();
        store.setAgentId(agentId).setStoreName(storeName).setStoreCover(storeCover);
        store.insert();
        AgentWallet agentWallet = new AgentWallet();
        agentWallet.setAgentId(agentId);
        agentWallet.insert();

        return agent;
    }

    @Override
    public Boolean updatePassword(String phone, String password, String code) {

        // 查询手机号是否存在
        Agent agent = baseMapper.selectOne(new QueryWrapper<Agent>()
                .eq("phone", phone));

        if (ObjectUtil.isNull(agent) ){
            throw new MyException(CodeMsg.AGENT_IS_NULL);
        }

        // 获取验证码
        String phoneCode = redisTemplate.getKey(phone);

        // 验证码已失效
        if (StrUtil.isBlank(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_IS_NULL);
        }

        if (!code.equals(phoneCode)) {
            throw new MyException(CodeMsg.VERIFICATION_CODE_ERROR);
        }

        agent.setPassword(SecureUtil.md5(password));

        return agent.updateById();
    }

    private void addAgentInfo(JSONObject json, Agent agent) {

        String openId = json.getStr("openid");
        String accessToken = json.getStr("access_token");

        JSONObject jsonObject = wxUtil.getUserInfo(openId, accessToken);

        agent.setNickname(jsonObject.getStr("nickname"))
                .setOpenid(openId)
                .setGender(jsonObject.getInt("sex"))
                .setStatus(0)
                .setCover(jsonObject.getStr("headimgurl"))
                .setRegion(jsonObject.getStr("province") + jsonObject.getStr("city"));
    }

}