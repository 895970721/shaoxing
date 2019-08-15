package com.nhxy.sxs.demo.exception;

import com.nhxy.sxs.demo.enums.StatusCode;
import lombok.Data;
import lombok.ToString;

/**
 * <p>Class: BaseBusinessException</p>
 * <p>运行时异常 基本业务异常 所有自定义异常的父类</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 9:57
 */

public class BaseBusinessException extends RuntimeException {
    private Integer code;

    public BaseBusinessException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
    }

    public BaseBusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
