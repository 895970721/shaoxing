package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.ViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "getAllView")
    public BaseResponse getAllView() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<ViewDTO> list = viewService.getALLViewDTO();
        response.setData(list);
        return response;
    }
}
