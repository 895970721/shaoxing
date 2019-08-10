package com.nhxy.sxs.demo.mapper;

import com.nhxy.sxs.demo.entity.IpAddress;

public interface IpAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpAddress record);

    int insertSelective(IpAddress record);

    IpAddress selectByPrimaryKey(Integer id);

    IpAddress selectByIp(String ip);

    int updateByPrimaryKeySelective(IpAddress record);

    int updateByPrimaryKey(IpAddress record);
}