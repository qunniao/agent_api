package com.zhancheng.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author BianShuHeng
 * @decription
 * @project FranchiseeDto
 * @date 2019/11/7 21:00
 */
@ApiModel("加盟商数据")
@Data
public class FranchiseeDto {

    private String code;
    private String trueName;
    private String phone;
    private String password;
    private Integer inviter;
    private String adminPhone;
    private Integer adminId;
    private String storeName;
    private String region;

}
