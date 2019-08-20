package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.AccessLog;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.AccessLogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Class: AccessLogController</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/15 16:26
 */
@RestController
@RequestMapping("/log")
@Api(tags = "网站日志操作接口")
public class AccessLogController {
    @Autowired
    AccessLogServiceImpl accessLogService;

    @GetMapping("/getlog")
    @ApiOperation("获取日志")
    public BaseResponse getLog(@RequestParam("page_num") Integer pageNum,
                               @RequestParam(value = "page_size", required = false) Integer pageSize) {
        List<AccessLog> accessLogList;
        if (pageSize == null) {
            accessLogList = accessLogService.getLog(pageNum);
        } else {
            accessLogList = accessLogService.getLog(pageNum, pageSize);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(accessLogList);
        return baseResponse;
    }
}
