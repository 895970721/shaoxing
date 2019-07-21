package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.View;

public interface ViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);
}