package com.nhxy.sxs.demo.entity;

public class HomePageContent {
    private Integer id;

    private Integer type;

    private String smallTitle;

    private String smallTitleContent;

    public HomePageContent(Integer id, Integer type, String smallTitle, String smallTitleContent) {
        this.id = id;
        this.type = type;
        this.smallTitle = smallTitle;
        this.smallTitleContent = smallTitleContent;
    }

    public HomePageContent() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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