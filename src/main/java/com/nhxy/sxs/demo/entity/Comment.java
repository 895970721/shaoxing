package com.nhxy.sxs.demo.entity;

import java.util.Date;

public class Comment {
    private Integer id;

    private String content;

    private Integer star;

    private Integer userId;

    private Integer viewId;

    private Date createTime;

    public Comment(Integer id, String content, Integer star, Integer userId, Integer viewId, Date createTime) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.userId = userId;
        this.viewId = viewId;
        this.createTime = createTime;
    }

    public Comment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}