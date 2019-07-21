package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.HomePage;

public interface HomePageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePage record);

    int insertSelective(HomePage record);

    HomePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePage record);

    int updateByPrimaryKey(HomePage record);
}