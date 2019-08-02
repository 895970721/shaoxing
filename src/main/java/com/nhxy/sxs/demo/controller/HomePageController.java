package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.HomePageDTO;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.HomePageServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>Class: HomePageController</p>
 *
 * @author FunnyChen
 * @version 1.0.0
 * @since 2019/7/24
 */
@RestController
@RequestMapping(value = "/city")
@Api(tags = "主页接口")
public class HomePageController {

    @Autowired
    private HomePageServiceImpl homePageService;

    /**
     * <p>获取城市信息
     * type:1为古城,2为水城,3为名城
     * </p>
     *
     * @param type
     * @return
     */
    @GetMapping(value = "getCity")
    public BaseResponse getCity(@RequestParam("city_type") Integer type) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        HomePageDTO homePageDTO = homePageService.getCity(type);
        response.setData(homePageDTO);
        return response;
    }
}
