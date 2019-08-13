package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.LikeFamous;
import com.nhxy.sxs.demo.entity.User;

import java.util.List;

public interface LikeFamousMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeFamous record);

    int insertSelective(LikeFamous record);

    LikeFamous selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeFamous record);

    int updateByPrimaryKey(LikeFamous record);

    List<LikeFamous> selectByUser(User user);

    LikeFamous selectByLikeFamousSelective(LikeFamous likeFamous);
}