package com.nhxy.sxs.demo.enums;

import java.util.Date;

/**
 * <p>enum: expTime</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 13:23
 */
public enum ExpTime {
    OneDay(new Date(System.currentTimeMillis() + 86400000L)),
    TwoDay(new Date(System.currentTimeMillis() + 172800000L)),
    OneWeek(new Date(System.currentTimeMillis() + 604800000L)),
    OneMonth(new Date(System.currentTimeMillis() + 2592000000L));
    private Date exp;

    ExpTime(Date exp) {
        this.exp = exp;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }


}
