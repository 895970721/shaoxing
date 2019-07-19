package com.nhxy.sxs.demo.entity;

public class User implements Role{
    private Integer id;

    private String username;

    private String password;

    private String fileName;

    public User(Integer id, String username, String password, String fileName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }
}