package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentStockQueryDto
 * @date 2019/11/2 13:58
 */
@Data
public class AgentStockQueryDto {

    private String productName;
    private Integer agentId;
    private Integer type;
}
