package com.zhancheng.core.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderNumUtil
 * @date 2019/10/28 13:58
 */
public class OrderNumUtil {

    //生成
    private final static Snowflake remitNumber = IdUtil.getSnowflake(1, 1);

    private final static Snowflake tradeNo = IdUtil.getSnowflake(1, 1);

    public static String generateOrderNumber(Integer orderType){

        String orderNumber = "";
        String random = System.currentTimeMillis() + "" + RandomUtil.randomInt(100, 10000);
        switch (orderType) {
            case 1:{
                orderNumber = "C" + random;
            }
            break;
            case 2:{
                orderNumber = "L" + random;
            }
            break;
            case 3:{
                orderNumber = "T" + random;
            }
            break;
            case 4:{
                orderNumber = "N" + random;
            }
            break;
            case 5:{
                orderNumber = "H" + random;
            }
            break;
            default:
            break;
        }

        return orderNumber;
    }

    // 生成打款号
    public static String generateRemitNumber(){

        return remitNumber.nextIdStr();
    }

    // 生成余额交易号
    public static String generateTradeNo(){

        return tradeNo.nextIdStr();
    }

}
