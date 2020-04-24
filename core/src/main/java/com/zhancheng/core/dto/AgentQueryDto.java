package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentQueryDto
 * @date 2019/10/30 17:50
 */
@Data
public class AgentQueryDto {

    @ApiModelProperty(name = "levelId", value = "等级id")
    private Integer levelId;
    @ApiModelProperty(name = "search", value = "搜索")
    private String search;
}
