package com.zhancheng.core.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.JsonObject;
import com.zhancheng.core.dao.StoreVisitorMapper;
import com.zhancheng.core.dao.VisitorHistoryMapper;
import com.zhancheng.core.entity.Store;
import com.zhancheng.core.entity.StoreVisitor;
import com.zhancheng.core.entity.VisitorHistory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BianShuHeng
 * @decription
 * @project AddStoreVisitor
 * @date 2019/10/29 14:34
 */
@Component
public class StoreUtil {

    @Resource
    private StoreVisitorMapper storeVisitorMapper;

    @Resource
    private HttpServletRequest request;

    @Transactional(rollbackFor = Exception.class)
    public Boolean addStoreVisitor(Store store, Integer uid){

        // 获取客户端ip
        String clientIP = ServletUtil.getClientIP(request, "HTTP_CLIENT_IP");
        System.err.println(clientIP);

        // 根据ip地址获取城市信息
        String jsonStr = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + clientIP);
        String address = "";
        if (StrUtil.isNotBlank(jsonStr)){
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            //省
            String region = jsonObject.getStr("region");
            //市
            String city = jsonObject.getStr("city");
            address = region + city;
        }

        System.err.println(address);

        StoreVisitor storeVisitor = storeVisitorMapper.selectOne(new QueryWrapper<StoreVisitor>()
                .eq("uid", uid).eq("store_id", 1));

        if (ObjectUtil.isNotNull(storeVisitor)){

            storeVisitor.setNum(storeVisitor.getNum() + 1).setIp(clientIP).setRegion(address);
            storeVisitor.updateById();
        }else {

            StoreVisitor storeVisitorInfo = new StoreVisitor();
            storeVisitorInfo.setUid(uid).setStoreId(store.getSid()).setManagerId(store.getAgentId())
                    .setIp(clientIP).setRegion(address).setNum(1);
            storeVisitorInfo.insert();
            storeVisitor = storeVisitorInfo;
        }

        VisitorHistory visitorHistory = new VisitorHistory();
        visitorHistory.setVisitorId(storeVisitor.getId()).setIp(clientIP);

        return visitorHistory.insert();
    }
}
