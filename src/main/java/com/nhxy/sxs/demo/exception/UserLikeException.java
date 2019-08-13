package com.nhxy.sxs.demo.exception;

import com.nhxy.sxs.demo.enums.StatusCode;

/**
 * <p>Class: UserLikeException</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 16:05
 */
public class UserLikeException extends BaseBusinessException {
    public UserLikeException(StatusCode statusCode) {
        super(statusCode);
    }

    public UserLikeException(int code, String msg) {
        super(code, msg);
    }
}
