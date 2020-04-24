package com.zhancheng.backstage.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.WalletHistoryService;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.vo.WalletHistoryListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.WalletHistory;

import javax.annotation.Resource;


/**
 * 钱包收支明细记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Api(tags = "钱包收支明细记录")
@RestController
@RequestMapping("/wallet/history")
public class WalletHistoryController {

    @Resource
    private WalletHistoryService walletHistoryService;

    @ApiOperation(value = "分页查询钱包收支明细记录列表")
    @ApiImplicitParam(name = "agentId", value = "代理id")
    @GetMapping("/page")
    public R<IPage<WalletHistoryListVO>> queryPage(PageDto<WalletHistory> pageDto, WalletHistoryQueryDTO walletHistoryQueryDTO) {

        return R.ok(walletHistoryService.queryPage(pageDto, walletHistoryQueryDTO));
    }

    @ApiOperation(value = "删除钱包收支明细记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(walletHistoryService.removeByIds(ids));
    }

}
