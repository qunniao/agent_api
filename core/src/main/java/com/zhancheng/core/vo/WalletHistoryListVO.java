package com.zhancheng.core.vo;

import com.zhancheng.core.entity.WalletHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BianShuHeng
 * @decription
 * @project WalletHistoryListVO
 * @date 2019/11/28 21:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WalletHistoryListVO extends WalletHistory {

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;
}
