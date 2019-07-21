package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.HomePageContent;

import java.util.List;

public interface HomePageContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePageContent record);

    int insertSelective(HomePageContent record);

    HomePageContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePageContent record);

    int updateByPrimaryKey(HomePageContent record);

    List<HomePageContent> selectByCityType(Integer type);
}