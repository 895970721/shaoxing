package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.dto.ViewDTO;
import com.nhxy.sxs.demo.entity.Picture;
import com.nhxy.sxs.demo.entity.View;

import java.util.List;

public interface ViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);

    List<Picture> selectParentPictureByPrimaryKey(Integer id);

    List<ViewDTO> getALLViewDTO();

    List<View> getAllView();

    List<View> getAllParentView();

    List<View> getAllSubView(Integer id);
}