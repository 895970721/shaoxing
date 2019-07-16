package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.Famous;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.FamousServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FamousController {
    @Autowired
    private FamousServiceImpl famousService;

    /**
     *查询所有名人
     */
    @GetMapping(value = "/getAllFamous")
    public BaseResponse getAllFamousByPage
    (@RequestParam("page_num") Integer page_num,
     @RequestParam("page_size") Integer page_size)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<Famous> list = famousService.getAllFamousByPage(page_num,page_size);
        response.setData(list);
        return response;
    }
}
