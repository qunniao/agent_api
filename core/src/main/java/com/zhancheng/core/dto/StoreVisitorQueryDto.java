package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project StoreVisitorQueryDto
 * @date 2019/10/29 16:45
 */
@Data
public class StoreVisitorQueryDto {

    @ApiModelProperty(name = "name", value = "名称")
    private String name;
    @ApiModelProperty(name = "startTime", value = "开始时间")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "结束时间")
    private String endTime;
}
