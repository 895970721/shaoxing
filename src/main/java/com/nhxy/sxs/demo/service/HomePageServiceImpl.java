package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.dto.HomePageDTO;
import com.nhxy.sxs.demo.entity.HomePage;
import com.nhxy.sxs.demo.entity.HomePageContent;
import com.nhxy.sxs.demo.mapper.HomePageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageMapper {

    @Autowired
    private HomePageMapper homePageMapper;

    @Autowired
    private HomePageContentServiceImpl homePageContentService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(HomePage record) {
        return 0;
    }

    @Override
    public int insertSelective(HomePage record) {
        return 0;
    }

    @Override
    public HomePage selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(HomePage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(HomePage record) {
        return 0;
    }

    @Override
    public HomePage selectByType(Integer type){
        return homePageMapper.selectByType(type);
    }

    @Override
    public HomePageDTO getCity(Integer type){
        HomePage homePage = homePageMapper.selectByType(type);
        List<HomePageContent> list = homePageContentService.selectByCityType(type);
        HomePageDTO homePageDTO = new HomePageDTO();
        BeanUtils.copyProperties(homePage,homePageDTO);
        homePageDTO.setSubTitleList(list);
        return homePageDTO;
    }
}
