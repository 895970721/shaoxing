package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureMapper {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Picture record) {
        return 0;
    }

    @Override
    public int insertSelective(Picture record) {
        return 0;
    }

    @Override
    public Picture selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Picture record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Picture record) {
        return 0;
    }

    @Override
    public List<Picture> selectPictureByViewId(Integer view_id) {
        return pictureMapper.selectPictureByViewId(view_id);
    }
}
