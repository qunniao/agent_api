package com.zhancheng.core.vo;

import com.zhancheng.core.entity.Reward;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardVo
 * @date 2019/11/13 16:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RewardVo extends Reward {

    private String nickname;
    private String agentPhone;
    private String rewardSourceNick;
    private String rewardSourcePhone;
}
