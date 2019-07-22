package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectByViewId(Integer viewId);

    Comment selectByUserIdAndViewId(Integer userId,Integer viewId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}