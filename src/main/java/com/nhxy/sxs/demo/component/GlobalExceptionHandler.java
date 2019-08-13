package com.nhxy.sxs.demo.component;

import com.nhxy.sxs.demo.exception.BaseBusinessException;
import com.nhxy.sxs.demo.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Class: GlobalExceptionHandler</p>
 * <P>全局异常处理</P>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 10:01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({BaseBusinessException.class})
    public BaseResponse BusinessExceptionHandler(BaseBusinessException e){
        log.error(e.toString());
        return new BaseResponse(e.getCode(),e.getMessage());
    }
}
