package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.AccessLog;

import java.util.List;

public interface AccessLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(Integer id);

    List<AccessLog> selectAll();

    int updateByPrimaryKeySelective(AccessLog record);

    int updateByPrimaryKey(AccessLog record);
}