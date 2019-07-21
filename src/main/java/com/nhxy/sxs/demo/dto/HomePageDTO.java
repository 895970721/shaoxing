package com.nhxy.sxs.demo.dto;

import com.nhxy.sxs.demo.entity.HomePageContent;
import lombok.Data;

import java.util.List;

@Data
public class HomePageDTO {
    private Integer id;

    private Integer cityType;

    private String cityName;

    private String cityContent;

    private List<HomePageContent> SubTitleList;
}
