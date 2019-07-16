package com.nhxy.sxs.demo.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.nhxy.sxs.demo.entity.Famous;
import com.nhxy.sxs.demo.mapper.FamousMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamousServiceImpl implements FamousMapper{

    @Autowired
    private FamousMapper famousMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Famous> getAllFamousByPage(int page_num,int page_size){
        PageHelper.startPage(page_num,page_size);
        List<Famous> list = famousMapper.getAllFamous();
        redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        stringRedisTemplate.opsForValue().set("user:name", "张三");
        return list;
    }

    @Override
    public List<Famous> getAllFamous(){
        return famousMapper.getAllFamous();
    }
}
