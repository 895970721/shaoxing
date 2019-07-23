package com.nhxy.sxs.demo.dto;

import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.entity.View;
import lombok.Data;

import java.util.List;

@Data
public class ViewDTO {

    private Integer id;

    private String title;

    private String fileContent;

    private List<Picture> pic_url;

    private List<SubViewDTO> subViewList;
}
