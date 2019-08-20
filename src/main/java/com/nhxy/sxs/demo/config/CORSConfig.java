package com.nhxy.sxs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Class: CORSConfig</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/15 15:34
 */
@Configuration
public class CORSConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
                        .allowedOrigins("*");
            }
        };
    }
}
