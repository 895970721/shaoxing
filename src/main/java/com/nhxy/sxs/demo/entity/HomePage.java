package com.nhxy.sxs.demo.entity;

public class HomePage {
    private Integer id;

    private Integer cityType;

    private String cityName;

    private String cityContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityContent() {
        return cityContent;
    }

    public void setCityContent(String cityContent) {
        this.cityContent = cityContent == null ? null : cityContent;
    }
}