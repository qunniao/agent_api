package com.zhancheng.core.util;

import cn.hutool.json.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BianShuHeng
 * @decription
 * @project SmsUtils
 * @date 2019/11/15 10:46
 */
public class SmsUtils {

    private static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    /**
     *产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    private static final String ACCESS_KEY_ID = "LTAIt5HT5tuBdPsR";

    /**
     * 秘钥
     */
    private static final String ACCESS_KEY_SECRET = "GrdKkl5crqEmJDlT70FzHQk4GKeAz2";

    /**
     * 签名
     */
    private static final String SIGN_NAME = "创新卓顺";

    private static final String REGION_ID = "cn-hangzhou";

    // 设置鉴权参数，初始化客户端
    private static DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static CommonResponse sendSms(String phone, String templateCode,String code) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        JSONObject code_json = new JSONObject();
        code_json.put("code", code);
        CommonRequest request = new CommonRequest();
        request.setSysDomain(DOMAIN);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", REGION_ID);
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", phone);
        // 短信签名名称。请在控制台签名管理页面签名名称一列查看（必须是已添加、并通过审核的短信签名）。
        request.putQueryParameter("SignName", SIGN_NAME);
        // 短信模板ID
        request.putQueryParameter("TemplateCode", templateCode);
        // 短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam",code_json.toString());
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse = client.getCommonResponse(request);
            logger.info("发送短信：{}",commonResponse);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return commonResponse;
    }

}
