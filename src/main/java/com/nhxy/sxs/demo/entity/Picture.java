package com.nhxy.sxs.demo.entity;

public class Picture {
    private Integer id;

    private String picUrl;

    private Integer viewId;

    public Picture(Integer id, String picUrl, Integer viewId) {
        this.id = id;
        this.picUrl = picUrl;
        this.viewId = viewId;
    }

    public Picture() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }
}