package com.zhancheng.agent.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.agent.applet.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.TransferDto;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Store;
import com.zhancheng.core.vo.TransferListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferController
 * @date 2019/11/8 15:31
 */
@Api(tags = "转账")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Resource
    private TransferService transferService;

    @ApiOperation(value = "代理转账")
    @ApiImplicitParam(name = "sid", value = "主键id")
    @PutMapping("/agent")
    public R<Boolean> info(@RequestBody TransferDto transferDto){

        return R.ok(transferService.agentTransfer(transferDto));
    }

    @ApiOperation(value = "分页查询转账记录列表")
    @GetMapping("/page")
    public R<IPage<TransferListVo>> queryPage(PageDto pageDto, TransferQueryDto transferQueryDto){

        return R.ok(transferService.selectPage(pageDto, transferQueryDto));
    }


}
