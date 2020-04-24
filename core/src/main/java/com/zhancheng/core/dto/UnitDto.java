package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription 单位 Dto
 * @project UnitDto
 * @date 2019/10/17 16:42
 */
@Data
public class UnitDto {
    @ApiModelProperty(name = "unitName", value = "单位名称", example = "1")
    private String unitName;
    @ApiModelProperty(name = "num", value = "单位数量", example = "1")
    private Integer num;
    @ApiModelProperty(name = "level", value = "单位层级", example = "1")
    private Integer level;
}
