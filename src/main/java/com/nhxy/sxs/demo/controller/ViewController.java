package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.service.ViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController {

    @Autowired
    private ViewServiceImpl viewService;

    @PostMapping(value = "getAllView")
    public List<ViewDTO> getAllView() {
        return viewService.getALLViewDTO();
    }
}
