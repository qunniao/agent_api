package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project BackOrderQueryDto
 * @date 2019/10/30 15:53
 */
@Data
public class BackOrderQueryDto {

    private Integer type;
    private Integer orderType;
    private String orderState;

    private String sortType;
}
