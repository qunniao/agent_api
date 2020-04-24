package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project ParamGroupListVo
 * @date 2019/10/19 15:30
 */
@Data
public class ParamGroupListVo {

    @ApiModelProperty(name = "pgId", value = "属性组id")
    private Integer pgId;
    @ApiModelProperty(name = "title", value = "属性组名称")
    private String title;
    @ApiModelProperty(name = "names", value = "多个属性名")
    private String names;
    @ApiModelProperty(name = "types", value = "关联的类型")
    private String typeNames;
    @ApiModelProperty(name = "gmtCreate", value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
}
