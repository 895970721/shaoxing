package com.nhxy.sxs.demo.service;

import com.github.pagehelper.PageHelper;
import com.nhxy.sxs.demo.entity.AccessLog;
import com.nhxy.sxs.demo.mapper.AccessLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Class: AccessLogServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/15 16:30
 */
@Service
@Slf4j
public class AccessLogServiceImpl implements AccessLogMapper {
    @Autowired
    AccessLogMapper accessLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(AccessLog record) {
        return 0;
    }

    @Override
    public int insertSelective(AccessLog record) {
        return 0;
    }

    @Override
    public AccessLog selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<AccessLog> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AccessLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AccessLog record) {
        return 0;
    }

    public List<AccessLog> getLog(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return accessLogMapper.selectAll();
    }
    public List<AccessLog> getLog(int pageNum) {
        return this.getLog(pageNum,20);
    }
}
