package com.nhxy.sxs.demo.entity;

import lombok.Data;

@Data
public class View {
    private Integer id;

    private String title;

    private String filename;

    private Integer parentViewId;

    public View(Integer id, String title, String filename, Integer parentViewId) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.parentViewId = parentViewId;
    }

    public View() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getParentViewId() {
        return parentViewId;
    }

    public void setParentViewId(Integer parentViewId) {
        this.parentViewId = parentViewId;
    }
}