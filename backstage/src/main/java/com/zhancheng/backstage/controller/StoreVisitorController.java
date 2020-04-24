package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.StoreVisitorService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.VisitorHistoryMapper;
import com.zhancheng.core.dto.StoreVisitorQueryDto;
import com.zhancheng.core.entity.StoreVisitor;
import com.zhancheng.core.entity.VisitorHistory;
import com.zhancheng.core.vo.StoreVisitorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺访客记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-29 14:14:15
 */
@Api(tags = "店铺访客记录")
@RestController
@RequestMapping("/storeVisitors")
public class StoreVisitorController {

    @Resource
    private StoreVisitorService storeVisitorService;

    @Resource
    private VisitorHistoryMapper visitorHistoryMapper;

    @ApiOperation(value = "分页查询店铺访客记录列表")
    @GetMapping("/list")
    public R<IPage<StoreVisitorVo>> list(PageDto<StoreVisitor> pageDto, StoreVisitorQueryDto storeVisitorQueryDto){
        return R.ok(storeVisitorService.selectPage(pageDto, storeVisitorQueryDto));
    }

    @ApiOperation(value = "查询店铺访客记录详情")
    @ApiImplicitParam(name = "id", value = "店铺访客记录id")
    @GetMapping("/info")
    public R<IPage<VisitorHistory>> info(PageDto<VisitorHistory> pageDto, Integer id){
        IPage<VisitorHistory> visitorHistoryIPage = visitorHistoryMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<VisitorHistory>().eq("visitor_id", id));
        return R.ok(visitorHistoryIPage);
    }

}
