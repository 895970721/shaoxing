package com.nhxy.sxs.demo.enums;

/**
 * <p>enum: expTime</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 13:23
 */
public enum ExpTime {
    OneDay(86400000L),
    TwoDay(172800000L),
    OneWeek(604800000L),
    OneMonth(2592000000L);
    private Long exp;

    ExpTime(Long exp) {
        this.exp = exp;
    }

    public Long getExp() {
        return exp;
    }


}
