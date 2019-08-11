package com.nhxy.sxs.demo.entity;

public class User implements Role{
    private Integer id;

    private String username;

    private String password;

    private String fileName;

    private String nickname;

    private String sign;

    public User(Integer id, String username, String password, String fileName, String nickname, String sign) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
        this.nickname = nickname;
        this.sign = sign;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
}