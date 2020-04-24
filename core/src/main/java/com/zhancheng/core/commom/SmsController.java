package com.zhancheng.core.commom;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.CommonResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.core.enums.SmsEnum;
import com.zhancheng.core.util.SmsUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @decription
 * @project SmsController
 * @date 2019/11/15 11:25
 */
@RestController
@RequestMapping("/sms")
@Api(tags = "发送短信")
public class SmsController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("send/sms")
    public void SendSms(String phone) {
        // 生成随机数
        String randomNumbers = RandomUtil.randomNumbers(6);
        // 发送短信
        SmsUtils.sendSms(phone, SmsEnum.SMS_CXZS.getTemplateCode(), randomNumbers);
        // 存入redis
        redisTemplate.setSmsCode(phone, randomNumbers);
    }
}
