package com.nhxy.sxs.demo.entity;

public class HomePage {
    private Integer id;

    private Integer type;

    private String name;

    private String filename;

    public HomePage(Integer id, Integer type, String name, String filename) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.filename = filename;
    }

    public HomePage() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}