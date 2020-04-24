package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderQueryDto
 * @date 2019/10/28 16:52
 */
@Data
public class OrderQueryDto {
    private String search;
    private Integer orderState;
    private Integer uid;
    private Integer agentId;
    private Integer orderType;
}
