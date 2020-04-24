package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.WalletHistoryService;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.vo.WalletHistoryListVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/walletHistorys")
public class WalletHistoryController {

    @Resource
    private WalletHistoryService walletHistoryService;

    @ApiOperation(value = "分页查询钱包收支明细记录列表")
    @ApiImplicitParam(name = "agentId", value = "代理id",required = true)
    @GetMapping("/page")
    public R<IPage<WalletHistoryListVO>> queryPage(PageDto<WalletHistory> pageDto, WalletHistoryQueryDTO walletHistoryQueryDTO) {

        return R.ok(walletHistoryService.queryPage(pageDto, walletHistoryQueryDTO));
    }


}
