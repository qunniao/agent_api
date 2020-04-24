package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project GroupPackageVo
 * @date 2019/11/7 15:31
 */
@Data
public class GroupPackageVo {

    private Integer gid;
    private String title;
    private String cover;
    private BigDecimal price;
    private Integer freightType;
    private BigDecimal freight;
    private BigDecimal discount;

    private List<GroupProductVo> groupProductVoList;

}
