package com.nhxy.sxs.demo.enums;

public enum StatusCode {
    Success(1,"数据获取成功"),
    Fail(0,"数据获取失败");
    private Integer Code;
    private String msg;
    StatusCode(Integer Code,String msg){
        this.Code = Code;
        this.msg = msg;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
