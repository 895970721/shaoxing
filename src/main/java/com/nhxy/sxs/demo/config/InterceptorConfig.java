package com.nhxy.sxs.demo.config;

import com.nhxy.sxs.demo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Class: InterceptorConfig</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 16:05
 */

//取消拦截器
//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**");    // 使用TokenInterceptor拦截器拦截所有请求
    }

    //todo
    // 设置具体的跨域访问网址
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}

