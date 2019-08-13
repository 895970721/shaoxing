package com.nhxy.sxs.demo.dto;

import lombok.Data;

/**
 * <p>Class: UserDTO</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/13 10:42
 */
@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String fileName;

    private String nickname;

    private String sign;

    public UserDTO(Integer id, String username, String nickname, String fileName, String sign) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.fileName = fileName;
        this.sign = sign;
    }
}
