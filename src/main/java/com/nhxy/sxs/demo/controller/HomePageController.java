package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.HomePageDTO;

import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.HomePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @Autowired
    private HomePageServiceImpl homePageService;

    /**
     * 获取城市信息
     * type:1为古城,2为水城,3为名城
     * @param type
     * @return
     */
    @GetMapping(value = "getCity")
    public BaseResponse getCity(@RequestParam("city_type") Integer type){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        HomePageDTO homePageDTO = homePageService.getCity(type);
        response.setData(homePageDTO);
        return response;
    }
}
