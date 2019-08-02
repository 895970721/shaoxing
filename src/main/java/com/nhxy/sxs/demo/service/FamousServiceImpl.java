package com.nhxy.sxs.demo.service;

import com.github.pagehelper.PageHelper;
import com.nhxy.sxs.demo.dto.FamousDTO;
import com.nhxy.sxs.demo.entity.Famous;
import com.nhxy.sxs.demo.mapper.FamousMapper;
import com.nhxy.sxs.demo.utils.FamousUtilImpl;
import com.nhxy.sxs.demo.utils.FileIOUtils;
import com.nhxy.sxs.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FamousServiceImpl implements FamousMapper {

    @Value("${file.content}")
    private String file_content;

    @Autowired
    private FamousMapper famousMapper;

    @Autowired
    private FamousUtilImpl famousUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public List<FamousDTO> getAllFamousByPage(int page_num, int page_size) {
        PageHelper.startPage(page_num, page_size);
        List<Famous> Famous_list = famousMapper.getAllFamous();
        //返回DTO列表
        List<FamousDTO> FamousDTO_list = new ArrayList<FamousDTO>();
        //读取本地文件数据,一次读取三个Famous对象
        for (int i = 0; i < page_size; i++) {
            //将Famous对象转换成String字符串,调用对象toString()方法
            String str = String.valueOf(Famous_list.get(i));
            //调用工具类,返回文件名
            String FileName = StringUtils.StringToArrayGetContent(str, 2, 10);
            //定义DTO返回的文件内容
            String FileContent = null;
            FileContent = FileIOUtils.getFileContent(file_content, FileName);
            //PO->DTO
            FamousDTO famousDTO = new FamousDTO();
            BeanUtils.copyProperties(Famous_list.get(i), famousDTO);
            famousDTO.setFile_content(FileContent);
            FamousDTO_list.add(famousDTO);
        }
        //redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        //stringRedisTemplate.opsForValue().set("user:name", "张三");
        return FamousDTO_list;
    }

    @Override
    public List<FamousDTO> getFamousById(Integer id) {
        Famous famous = famousMapper.selectByPrimaryKey(id);
        return famousUtil.getFamousContent(famous);
    }

    @Override
    public List<String> getFamousByFuzzyQuery(String word) {
        return famousMapper.getFamousByFuzzyQuery(word);
    }

    @Override
    public List<FamousDTO> getFamousByName(String name) {
        Famous famous = famousMapper.selectFamousByName(name);
        return famousUtil.getFamousContent(famous);
    }

    @Override
    public Famous selectByPrimaryKey(Integer id) {
        return famousMapper.selectByPrimaryKey(id);
    }

    @Override
    public Famous selectFamousByName(String name) {
        return famousMapper.selectFamousByName(name);
    }

    @Override
    public List<Famous> getAllFamous() {
        return famousMapper.getAllFamous();
    }
}
