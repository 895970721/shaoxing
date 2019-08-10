package com.nhxy.sxs.demo.entity;

import java.util.Objects;

public class LikeView {
    private Integer id;

    private Integer viewId;

    private String viewTitile;

    private String pictureUrl;

    private Integer userId;

    public LikeView(Integer id, Integer viewId, String viewTitile, String pictureUrl, Integer userId) {
        this.id = id;
        this.viewId = viewId;
        this.viewTitile = viewTitile;
        this.pictureUrl = pictureUrl;
        this.userId = userId;
    }

    public LikeView() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getViewTitile() {
        return viewTitile;
    }

    public void setViewTitile(String viewTitile) {
        this.viewTitile = viewTitile == null ? null : viewTitile.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeView likeView = (LikeView) o;
        return Objects.equals(id, likeView.id) &&
                Objects.equals(viewId, likeView.viewId) &&
                Objects.equals(viewTitile, likeView.viewTitile) &&
                Objects.equals(pictureUrl, likeView.pictureUrl) &&
                Objects.equals(userId, likeView.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, viewId, viewTitile, pictureUrl, userId);
    }
}