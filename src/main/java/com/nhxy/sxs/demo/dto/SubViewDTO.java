package com.nhxy.sxs.demo.dto;

import com.nhxy.sxs.demo.entity.Picture;
import lombok.Data;

import java.util.List;

@Data
public class SubViewDTO {

    private Integer id;

    private String title;

    private String fileContent;

    private List<Picture> pic_url;
}
