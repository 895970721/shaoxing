package com.nhxy.sxs.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * <p>Interface: TokenEntity</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/17 23:29
 */
@Data
public class TokenEntity {
    String username;//用户名
    String token;//token的值
    Date createDate;//创建时间
    Date expiresDate;//过期时间

    public TokenEntity() {
        this.createDate = new Date();
    }

    public TokenEntity(String username, String token, Date expiresDate) {
        this.username = username;
        this.token = token;
        this.createDate = new Date();
        this.expiresDate = expiresDate;
    }

    public TokenEntity(String username, String token,Date createDate, Date expiresDate) {
        this.username = username;
        this.token = token;
        this.createDate = createDate;
        this.expiresDate = expiresDate;
    }

}
