package com.zhancheng.core.dao;

import com.zhancheng.core.entity.GroupProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.GroupProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组合套餐的产品
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-06 21:06:23
 */
@Repository
public interface GroupProductMapper extends BaseMapper<GroupProduct> {

    /**
     * 根据套餐id 删除所有的套餐商品
     *
     * @param gid 套餐id
     * @return
     */
    Boolean deleteByGid(Integer gid);

    List<GroupProductVo> queryListByGid(Integer gid);

}
