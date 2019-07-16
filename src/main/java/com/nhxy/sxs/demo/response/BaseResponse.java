package com.nhxy.sxs.demo.response;

import com.nhxy.sxs.demo.enums.StatusCode;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;
    public BaseResponse(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
    public BaseResponse(Integer Code,String msg){
        this.code = Code;
        this.msg = msg;
    }
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponse(T data, StatusCode statusCode) {
        this.data = data;
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
}
