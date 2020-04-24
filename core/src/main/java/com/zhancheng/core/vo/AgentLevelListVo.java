package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project AgentLevelListVo
 * @date 2019/11/1 11:01
 */
@Data
public class AgentLevelListVo {

    @ApiModelProperty(name = "id", value = "代理等级id")
    private Integer id;
    @ApiModelProperty(name = "level", value = "代理等级")
    private String level;
    @ApiModelProperty(name = "nick", value = "代理等级别名")
    private String nick;
    @ApiModelProperty(name = "stockDiscount", value = "进货折扣：按照零售价的等比例折扣计算")
    private BigDecimal stockDiscount;
    @ApiModelProperty(name = "stockType", value = "首次进货类型：1.代理选购2组合套餐")
    private Integer stockType;
    @ApiModelProperty(name = "isTask", value = "加入任务：0.无任务1.有任务")
    private Integer isTask;
    @ApiModelProperty(name = "headcount", value = "代理人数")
    private Integer headcount;
    @ApiModelProperty(name = "alreadyAgent", value = "已代理")
    private Integer alreadyAgent;
    @ApiModelProperty(name = "applying", value = "申请中")
    private Integer applying;

}
