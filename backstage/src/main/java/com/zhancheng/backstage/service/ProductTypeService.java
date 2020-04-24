package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.ProductType;
import com.zhancheng.core.vo.ProductTypeVo;

import java.util.List;

/**
 * 产品类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
public interface ProductTypeService extends IService<ProductType> {

    /**
     * 分页查询产品类目列表
     * @param pageDto 分页参数
     * @param sortType 排序方式：默认 sort倒序; 1；时间升序, 2：时间倒序
     * @return IPage<ProductType>
     */
    IPage<ProductType> selectPage(PageDto<ProductType> pageDto, String sortType);

    /**
     * 分页查询产品类目列表
     * @return List<ProductTypeVo>
     */
    List<ProductType> selectList();

    /**
     * 查询产品类目详情
     * @param tid 主键Id
     * @return
     */
    ProductType info(Integer tid);

    /**
     * 添加产品类目
     * @param productType 产品类目数据
     * @return Boolean
     */
    Boolean insert(ProductType productType);

    /**
     * 修改产品类目
     * @param productType 产品类目数据
     * @return Boolean
     */
    Boolean update(ProductType productType);

    /**
     * 批量删除产品类目
     * @param tids 产品类目id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> tids);
}

