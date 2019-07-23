package com.nhxy.sxs.demo.dto;

import lombok.Data;

@Data
public class FamousDTO {
    private Integer id;

    private String title;

    private String file_content;

    private String pic_url;

    public FamousDTO() {

    }
}
