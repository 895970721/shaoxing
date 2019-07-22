package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.mapper.UserMapper;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <p>Class: UserServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/18 18:27
 */

@Slf4j
@Service
public class UserServiceImpl implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Value("${systemParam.user.name_min_length}")
    int usernameMinLength;
    @Value("${systemParam.user.password_max_length}")
    int passwordMaxLength;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 用户注册服务
     * 判断字段合法性
     *
     * @param user
     * @return
     */

    public BaseResponse register(User user) {
        BaseResponse baseResponse;

        if (user.getUsername().length() < usernameMinLength) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("用户名长度过短");
            return baseResponse;
        }
        if (user.getPassword().length() > passwordMaxLength) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("密码长度错误");
            return baseResponse;
        }
        if (userMapper.selectByUserName(user.getUsername()) != null) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("用户名重复，请重试");
            return baseResponse;
        }
        user.setPassword(MD5Util.encode(user.getPassword()));//MD5加密
        userMapper.insertSelective(user);
        return new BaseResponse(StatusCode.Success);
    }

    public BaseResponse login(User user) {
        BaseResponse baseResponse;
        User userFormDB = userMapper.selectByUserName(user.getUsername());
        if (userFormDB == null) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("用户名错误");
            return baseResponse;
        }
        if (!MD5Util.encode(user.getPassword()).equals(userFormDB.getPassword())) {//密码不匹配
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("密码错误");
            return baseResponse;
        }
        return new BaseResponse(StatusCode.Success);
    }

    @Value("${systemParam.user.imagefile.path}")
    String pathname;

    public int updateImageByPrimaryKey(User record, MultipartFile file) {
        if (file.getOriginalFilename().length() < 80) {

        }
        File image = new File(pathname  + file.getOriginalFilename());
        if (!image.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            image.getParentFile().mkdir();
            try {
                //创建文件
                image.createNewFile();
            } catch (IOException e) {
                log.error(e.toString()+"图片文件创建错误");
            }
        }
        FileOutputStream os=null;
        try {
            os=new FileOutputStream(image);
            os.write(file.getBytes());
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        record.setFileName(image.getPath());
        return updateByPrimaryKeySelective(record);
    }

    public byte[] getImage(User user){
        File image=new File(user.getFileName());
        FileInputStream is=null;
        byte[] imageByte=null;
        try {
            is=new FileInputStream(image);
            imageByte=new byte[is.available()];
            is.read(imageByte);
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        return imageByte;
    }
}
