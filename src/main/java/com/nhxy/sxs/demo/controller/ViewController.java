package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.ParentViewDTO;
import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.ViewServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Class: ViewController</p>
 *
 * @author FunnyChen
 * @version 1.0.0
 * @since 2019/7/24
 */
@RestController
@RequestMapping(value = "/view")
@Api(tags = "景点接口")
public class ViewController {

    @Autowired
    private ViewServiceImpl viewService;

    /**
     * 获取所有父景点信息
     *
     * @return
     */
    @PostMapping(value = "getAllParentView")
    public BaseResponse getAllParentView() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getAllParentViewDTO();
        response.setData(list);
        return response;
    }

    /**
     * 通过父景点id获取子景点信息
     *
     * @param parent_view_id
     * @return
     */
    @GetMapping(value = "getSubViewByParentId")
    public BaseResponse getSubViewByParentId(@RequestParam("id") Integer parent_view_id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getAllSubViewDTOByParentId(parent_view_id);
        response.setData(list);
        return response;
    }

    /**
     * 通过id获取景点信息
     *
     * @param view_id
     * @return
     */
    @GetMapping(value = "getViewById")
    public BaseResponse getViewById(@RequestParam("id") Integer view_id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getViewDTOById(view_id);
        response.setData(list);
        return response;
    }


    /**
     * 通过父景点id获取获取景点信息
     */
    @GetMapping(value = "getViewByParentId")
    public BaseResponse getViewByParentId(@RequestParam("id") Integer parent_view_id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ParentViewDTO> list = viewService.getViewByParentId(parent_view_id);
        if (list == null) {
            response = new BaseResponse(StatusCode.Fail);
        }
        response.setData(list);
        return response;
    }
}
