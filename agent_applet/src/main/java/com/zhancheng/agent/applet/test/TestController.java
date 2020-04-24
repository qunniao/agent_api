package com.zhancheng.agent.applet.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.JsonObject;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.dao.AgentMapper;
import com.zhancheng.core.dao.OrderInfoMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.dto.ProductQueryDto;
import com.zhancheng.core.entity.Agent;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.util.SmsUtils;
import com.zhancheng.core.util.StoreUtil;
import com.zhancheng.core.util.WxUtil;
import com.zhancheng.core.vo.ProductCountVo;
import com.zhancheng.core.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project TestController
 * @date 2019/10/19 10:13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WxUtil wxUtil;

    @Resource
    private AgentMapper agentMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @GetMapping("/1")
    public Object queryProductTest(String code) {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        paramMap.put("appid", "wxf2677d625e6881f4");
        paramMap.put("secret", "28947839a5c50b2a849d0fff5e311f5e");
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");

        String accessToken = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token", paramMap);

        System.err.println(accessToken);
        JSONObject jsonObject = JSONUtil.parseObj(accessToken);
        map.put("access_token", jsonObject.getStr("access_token"));
        map.put("openid", jsonObject.getStr("openid"));
        map.put("lang", "zh_CN");
        String userInfo = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo", map);

        System.err.println(userInfo);
        JSONObject json = JSONUtil.parseObj(userInfo);
        System.err.println(json);
        return userInfo;
    }
    @PostMapping("2")
    public Object test2(@RequestBody String notifyXml) throws Exception{

        Map<String, String> notify = WXPayUtil.xmlToMap(notifyXml);

        String totalFee = notify.get("total_fee");

        System.err.println(totalFee);
        OrderInfo orderInfo = orderInfoMapper.selectById(142);

        BigDecimal multiply = orderInfo.getPayMoney().multiply(new BigDecimal(100));
        int intValue = orderInfo.getPayMoney().multiply(new BigDecimal("100")).intValue();

        //判断金额是否一致
        if (!totalFee.equals(String.valueOf(intValue))) {
            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单金额不一致]]></return_msg>" + "</xml>";
        }

        return true;
    }
    @PostMapping("3")
    public Object test3(String phone){
        String randomNumbers = RandomUtil.randomNumbers(6);
        CommonResponse response = SmsUtils.sendSms(phone, "SMS_169505166", randomNumbers);
        System.out.println(response);
        redisTemplate.setSmsCode(phone, randomNumbers);
        return randomNumbers;
    }


}
