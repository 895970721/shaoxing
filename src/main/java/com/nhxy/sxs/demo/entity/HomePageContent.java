package com.nhxy.sxs.demo.entity;

public class HomePageContent {
    private Integer id;

    private Integer cityType;

    private String smallTitle;

    private String smallTitleContent;

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

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle == null ? null : smallTitle.trim();
    }

    public String getSmallTitleContent() {
        return smallTitleContent;
    }

    public void setSmallTitleContent(String smallTitleContent) {
        this.smallTitleContent = smallTitleContent == null ? null : smallTitleContent.trim();
    }
}