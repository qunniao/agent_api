package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.TransferQueryDto;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.TransferListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 转账记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-08 14:40:29
 */
@Api(tags = "转账记录")
@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Resource
    private TransferService transferService;

    @ApiOperation(value = "分页查询转账记录列表")
    @GetMapping("/page")
    public R<IPage<TransferListVo>> queryPage(PageDto pageDto, TransferQueryDto transferQueryDto){

        return R.ok(transferService.selectPage(pageDto, transferQueryDto));
    }


}
