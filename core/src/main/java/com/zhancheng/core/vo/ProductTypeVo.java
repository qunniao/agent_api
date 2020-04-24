package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductTypeVo
 * @date 2019/10/21 14:29
 */
@Data
public class ProductTypeVo {
    private Integer tid;
    private String typeName;

    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private List<ProductTypeVo> productTypeList;
}
