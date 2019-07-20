package com.nhxy.sxs.demo.service;

import com.github.pagehelper.PageHelper;
import com.nhxy.sxs.demo.dto.FamousDTO;
import com.nhxy.sxs.demo.entity.Famous;
import com.nhxy.sxs.demo.mapper.FamousMapper;
import com.nhxy.sxs.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FamousServiceImpl implements FamousMapper{

    @Value("${file.content}")
    private String file_content;

    @Autowired
    private FamousMapper famousMapper;

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
        for(int i=0;i<3;i++){
            //将Famous对象转换成String字符串,调用对象toString()方法
            String str = String.valueOf(Famous_list.get(i));
            //调用工具类,返回文件名
            String FileName = StringUtils.GetFileName(str);
            //定义DTO返回的文件内容
            String FileContent = null;
            FileInputStream fis = null;
            InputStreamReader reader;
            BufferedReader bufferedReade;
            try {
                //当前读取的文件的字符集
                String encoding = "utf-8";
                //当前读取的字符
                int lineText;
                File file = new File(file_content + FileName);
                fis = new FileInputStream(file);
                //将文件输入字节流转成输入字符流
                reader = new InputStreamReader(fis, encoding);
                //包装该字符流具有缓冲区功能,提高效率
                bufferedReade = new BufferedReader(reader);
                //字符串拼接
                StringBuilder sBuilder = new StringBuilder();
                sBuilder.append("\t\t\t\t");
                while ((lineText = bufferedReade.read()) != -1) {
                    sBuilder.append((char) lineText);
                }
                FileContent = sBuilder.toString().replace(" ", "").replace("\n","\n\t\t\t\t");
            } catch (FileNotFoundException e) {
                log.error("文件不存在或者文件不可读或者文件是目录");
            } catch (IOException e) {
                log.error("读取过程存在异常");
            }finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("文件关闭失败");
                }
            }
            //PO->DTO
            FamousDTO famousDTO = new FamousDTO();
            BeanUtils.copyProperties(Famous_list.get(i),famousDTO);
            famousDTO.setFile_content(FileContent);
            FamousDTO_list.add(famousDTO);
        }
        //redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        //stringRedisTemplate.opsForValue().set("user:name", "张三");
        return FamousDTO_list;
    }

    @Override
    public List<Famous> getAllFamous(){
        return famousMapper.getAllFamous();
    }
}
