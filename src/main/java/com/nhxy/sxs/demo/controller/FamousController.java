package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.FamousDTO;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.FamousServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Class: FamousController</p>
 *
 * @author FunnyChen
 * @version 1.0.0
 * @since 2019/7/24
 */
@RestController
@RequestMapping(value = "/famous")
public class FamousController {
    @Autowired
    private FamousServiceImpl famousService;

    /**
     * <p>查询所有名人</p>
     *
     * @param page_num
     * @param page_size
     * @return
     */
    @GetMapping(value = "/getAllFamousByPage")
    public BaseResponse getAllFamousByPage
    (@RequestParam("page_num") Integer page_num,
     @RequestParam("page_size") Integer page_size) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<FamousDTO> list = famousService.getAllFamousByPage(page_num, page_size);
        response.setData(list);
        return response;
    }
}
