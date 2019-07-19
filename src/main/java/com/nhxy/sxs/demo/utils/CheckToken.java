package com.nhxy.sxs.demo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Annotation: CheckToken</p>
 * 这个注解用于标明某类或某方法的执行需要鉴权（token）<p></p>
 * 值 required()如果为false,则该方法或该类不需要检查token
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/17 22:05
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {
    final static int user_tpye=1;
    final static int admin_tpye=2;//fatrue 用户类型
    boolean required() default true;
    int type() default user_tpye;
}
