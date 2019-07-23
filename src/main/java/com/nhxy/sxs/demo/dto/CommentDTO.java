package com.nhxy.sxs.demo.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>Class: CommentDTO</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/23 16:27
 */
@Data
public class CommentDTO {

    private String content;

    private Integer star;

    private String username;

    private String picturePath;

    private Integer viewId;

    private Date createTime;

    public CommentDTO() {

    }

    public CommentDTO(String content, Integer star, String username, String picturePath, Integer viewId, Date createTime) {
        this.content = content;
        this.star = star;
        this.username = username;
        this.picturePath = picturePath;
        this.viewId = viewId;
        this.createTime = createTime;
    }
}
