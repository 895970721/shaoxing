package com.nhxy.sxs.demo.entity;

public class LikeFamous {
    private Integer id;

    private Integer famousId;

    private String famousTitile;

    private String pictureUrl;

    private Integer userId;

    public LikeFamous(Integer id, Integer famousId, String famousTitile, String pictureUrl, Integer userId) {
        this.id = id;
        this.famousId = famousId;
        this.famousTitile = famousTitile;
        this.pictureUrl = pictureUrl;
        this.userId = userId;
    }

    public LikeFamous() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamousId() {
        return famousId;
    }

    public void setFamousId(Integer famousId) {
        this.famousId = famousId;
    }

    public String getFamousTitile() {
        return famousTitile;
    }

    public void setFamousTitile(String famousTitile) {
        this.famousTitile = famousTitile == null ? null : famousTitile.trim();
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
}