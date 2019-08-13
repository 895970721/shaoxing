package com.nhxy.sxs.demo.exception;

import com.nhxy.sxs.demo.enums.StatusCode;

/**
 * <p>Class: UserException</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 10:28
 */
public class UserException extends BaseBusinessException {
    public UserException(StatusCode statusCode) {
        super(statusCode);
    }

    public UserException(int code, String msg) {
        super(code, msg);
    }
}
