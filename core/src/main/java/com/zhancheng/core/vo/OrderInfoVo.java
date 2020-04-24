package com.zhancheng.core.vo;

import com.zhancheng.core.entity.OrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderInfoVo
 * @date 2019/10/29 18:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfoVo extends OrderInfo {
    private String userAgentLevel;
    private String storeCover;
    private String storeName;
    private String storeAgentLevel;
    private List<OrderProductBackVo> orderProductBackList;
}
