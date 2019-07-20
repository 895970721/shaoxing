package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.dto.FamousDTO;
import com.nhxy.sxs.demo.entity.Famous;


import java.util.List;


public interface FamousMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Famous record);
//
//    int insertSelective(Famous record);
//
//    Famous selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Famous record);
//
//    int updateByPrimaryKey(Famous record);

    List<Famous> getAllFamous();

    List<FamousDTO> getAllFamousByPage(int page_num, int page_size);
}
