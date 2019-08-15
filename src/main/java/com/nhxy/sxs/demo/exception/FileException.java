package com.nhxy.sxs.demo.exception;

import com.nhxy.sxs.demo.enums.StatusCode;

/**
 * <p>Class: FileException</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/15 10:14
 */
public class FileException extends BaseBusinessException {
    public FileException(StatusCode statusCode) {
        super(statusCode);
    }

    public FileException(int code, String msg) {
        super(code, msg);
    }
}
