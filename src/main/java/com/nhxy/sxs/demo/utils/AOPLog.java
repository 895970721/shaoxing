package com.nhxy.sxs.demo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Annotation: AOPLog</p>
 * 用于记录用户登录信息等等的注解
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/7 15:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AOPLog {
    boolean required() default true;
}
