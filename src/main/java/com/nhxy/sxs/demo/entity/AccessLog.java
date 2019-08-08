package com.nhxy.sxs.demo.entity;

import java.util.Date;

public class AccessLog {
    private Integer id;

    private Integer userId;

    private String userName;

    private String ip;

    private String ua;

    private Date accessTime;

    private String method;

    public AccessLog(Integer id, Integer userId, String userName, String ip, String ua, Date accessTime, String method) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.ip = ip;
        this.ua = ua;
        this.accessTime = accessTime;
        this.method = method;
    }

    public AccessLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }
}