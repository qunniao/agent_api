package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project WalletHistoryDTO
 * @date 2019/11/28 21:00
 */
@Data
public class WalletHistoryQueryDTO {

    @ApiModelProperty(name = "agentId", value = "代理id", example = "1")
    private Integer agentId;
}
