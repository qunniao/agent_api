package com.zhancheng.backstage.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentHistoryService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.AgentHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


/**
 * 代理变更记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-31 10:47:24
 */
@Api(tags = "代理变更记录")
@RestController
@RequestMapping("/agentHistory")
public class AgentHistoryController {

    @Resource
    private AgentHistoryService agentHistoryService;

    @ApiOperation(value = "查询代理注册时的信息")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{agentId}")
    public R<AgentHistory> queryInfo(@PathVariable("agentId") Integer agentId){

        return R.ok(agentHistoryService.queryInfo(agentId));
    }

}
