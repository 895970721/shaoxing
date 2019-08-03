package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.LikeView;
import com.nhxy.sxs.demo.entity.User;

import java.util.List;

public interface LikeViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeView record);

    int insertSelective(LikeView record);

    LikeView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeView record);

    int updateByPrimaryKey(LikeView record);

    List<LikeView> selectByUser(User record);

    List<LikeView> selectByLikeViewSelective(LikeView record);
}