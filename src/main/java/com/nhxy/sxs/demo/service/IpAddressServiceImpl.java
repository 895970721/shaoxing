package com.nhxy.sxs.demo.service;

import com.maxmind.geoip2.DatabaseReader;
import com.nhxy.sxs.demo.entity.IpAddress;
import com.nhxy.sxs.demo.mapper.IpAddressMapper;
import com.nhxy.sxs.demo.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Class: IpAddressServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/10 23:01
 */
@Service
@Slf4j
public class IpAddressServiceImpl implements IpAddressMapper {
    @Autowired
    IpAddressMapper ipAddressMapper;
    @Autowired
    DatabaseReader databaseReader;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(IpAddress record) {
        return 0;
    }

    @Override
    public int insertSelective(IpAddress record) {
        return 0;
    }

    @Override
    public IpAddress selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public IpAddress selectByIp(String ip) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(IpAddress record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(IpAddress record) {
        return 0;
    }

    public String queryAddress(String ip) throws Exception {
        IpAddress ipAddress = ipAddressMapper.selectByIp(ip);
        if (ipAddress == null) {//如果数据库没有此条记录
            ipAddress = new IpAddress();
            ipAddress.setIp(ip);
            try {
                ipAddress.setCountry(IpUtil.getCountry(databaseReader, ip));
                ipAddress.setProvince(IpUtil.getProvince(databaseReader, ip));
                ipAddress.setCity(IpUtil.getCity(databaseReader, ip));
                ipAddress.setAddress(IpUtil.getAddress(databaseReader, ip));
                ipAddress.setLatitude(IpUtil.getLatitude(databaseReader, ip).toString());
                ipAddress.setLongitude(IpUtil.getLongitude(databaseReader, ip).toString());
                ipAddressMapper.insertSelective(ipAddress);
            } catch (IOException e) {
                log.error("ip数据库读取错误(.mmdb数据库) "+e.toString());
            }
        }
        return ipAddress.getAddress();
    }
}
