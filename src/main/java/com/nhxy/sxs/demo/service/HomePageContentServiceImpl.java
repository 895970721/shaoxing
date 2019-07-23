package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.HomePageContent;
import com.nhxy.sxs.demo.mapper.HomePageContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageContentServiceImpl implements HomePageContentMapper {

    @Autowired
    private HomePageContentMapper homePageContentMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(HomePageContent record) {
        return 0;
    }

    @Override
    public int insertSelective(HomePageContent record) {
        return 0;
    }

    @Override
    public HomePageContent selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(HomePageContent record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(HomePageContent record) {
        return 0;
    }

    @Override
    public List<HomePageContent> selectByCityType(Integer type) {
        return homePageContentMapper.selectByCityType(type);
    }
}
