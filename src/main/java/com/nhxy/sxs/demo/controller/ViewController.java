package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.ViewServiceImpl;
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
public class ViewController {

    @Autowired
    private ViewServiceImpl viewService;

    @PostMapping(value = "getAllParentView")
    public BaseResponse getAllParentView() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getAllParentViewDTO();
        response.setData(list);
        return response;
    }

    @GetMapping(value = "getSubViewByParentId")
    public BaseResponse getSubViewByParentId(@RequestParam("id") Integer parent_view_id){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getAllSubViewDTOByParentId(parent_view_id);
        response.setData(list);
        return response;
    }

    @GetMapping(value = "getViewById")
    public BaseResponse getViewByParentId(@RequestParam("id") Integer view_id){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getViewDTOById(view_id);
        response.setData(list);
        return response;
    }

}
