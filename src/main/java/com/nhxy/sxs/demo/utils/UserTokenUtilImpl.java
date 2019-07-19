package com.nhxy.sxs.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nhxy.sxs.demo.entity.Role;
import com.nhxy.sxs.demo.entity.TokenEntity;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.ExpTime;
import com.nhxy.sxs.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * <p>Class: UserTokenUtil</p>
 * 实现TokenUtil接口
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/17 23:37
 */
@Component
public class UserTokenUtilImpl implements TokenUtil {

    private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public TokenEntity create(Role role, ExpTime expTime) {
        TokenEntity userTokenEntity = new TokenEntity();//此时初始化了createDate
        create0(role, userTokenEntity, expTime);
        return userTokenEntity;
    }

    /**
     * 方法通过传入token来验证token是否合法<br>
     *
     * @param token 需要验证的token
     * @return 验证通过则返回<code>ture</code><br>
     * 验证失败则返回<code>false</code>
     */
    @Override
    public boolean verifie(String token) {
        User user = getUser(token);
        try {
            JWTVerifier verifie = JWT.require(Algorithm.HMAC256(user.getPassword()))
                    .acceptExpiresAt(0)
                    .build();
            DecodedJWT jwt1 = verifie.verify(token);
        } catch (Exception e) {
            if (e instanceof JWTVerificationException) {
                logger.debug("验证失败", e);
            } else logger.error("未处理的错误", e);
            return false;
        }
        return true;
    }

    /**
     * <p>验证token是否是过期</p>
     * 如果过期时间超过一个月也会被判定为无效token
     *
     * @param token 传入token字符串
     * @return 如果过期，则返回ture<br>flase otherwhise
     * <br>
     */
    @Override
    public boolean isExpire(String token) {
        TokenEntity tokenEntity = getTokenEntity(token);
        return tokenEntity.getExpiresDate().after(new Date());
    }


    @Override
    public String reSign(String token, ExpTime expTime) {
        TokenEntity tokenEntity = getTokenEntity(token);
        User user = userMapper.selectByUserName(tokenEntity.getUsername());
        if ((tokenEntity.getCreateDate().getTime() + 2592000000L) > System.currentTimeMillis()) {//即使超过了create时间一个月
            return create0(user, tokenEntity, expTime).getToken();
        } else {
            return null;
        }
    }

    /**
     * private，封装了生成token的的行为
     */
    private TokenEntity create0(Role role, TokenEntity tokenEntity, ExpTime expTime) {
        try {
            String token = JWT.create()
                    .withExpiresAt(expTime.getExp())//设置默认过期时间
                    .withClaim("username", role.getUsername())
                    .withClaim("createDate", tokenEntity.getCreateDate())
                    .sign(Algorithm.HMAC256(role.getPassword()));
            tokenEntity.setExpiresDate(expTime.getExp());
            tokenEntity.setUsername(role.getUsername());
            tokenEntity.setToken(token);
        }
        catch (Exception e){
            logger.debug("token创建失败");
        }
        return tokenEntity;
    }

    public TokenEntity getTokenEntity(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getClaim("username").asString();
        Date createDate = jwt.getClaim("createDate").asDate();
        Date expires = jwt.getExpiresAt();
        return new TokenEntity(username, token, createDate, expires);
    }

    public User getUser(String token) {
        TokenEntity tokenEntity = getTokenEntity(token);
        return userMapper.selectByUserName(tokenEntity.getUsername());
    }
}
