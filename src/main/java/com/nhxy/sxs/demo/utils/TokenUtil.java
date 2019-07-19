package com.nhxy.sxs.demo.utils;

import com.nhxy.sxs.demo.entity.Role;
import com.nhxy.sxs.demo.entity.TokenEntity;
import com.nhxy.sxs.demo.enums.ExpTime;

/**
 * <p>Interface: TokenUtil</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/17 22:57
 */
public interface TokenUtil {
    public TokenEntity create(Role role, ExpTime expTime) throws Exception;

    public boolean verifie(String token);

    public boolean isExpire(String token);

    public String reSign(String token,ExpTime expTime);
}
