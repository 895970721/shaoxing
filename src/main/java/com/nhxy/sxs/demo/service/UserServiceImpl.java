package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.dto.UserDTO;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.exception.BaseBusinessException;
import com.nhxy.sxs.demo.exception.FileException;
import com.nhxy.sxs.demo.exception.UserException;
import com.nhxy.sxs.demo.mapper.UserMapper;
import com.nhxy.sxs.demo.utils.MD5Util;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
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
    @Autowired
    private UserTokenUtilImpl tokenUtil;

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
     * @param
     */

    public UserDTO register(String username, String password) throws BaseBusinessException {
        StatusCode statusCode;

        if (username.length() < usernameMinLength) {
            statusCode = StatusCode.UserNameLengthError;
            throw new UserException(statusCode);
        }
        if (password.length() > passwordMaxLength) {
            statusCode = StatusCode.PasswordLengthError;
            throw new UserException(statusCode);
        }
        if (userMapper.selectByUserName(username) != null) {
            statusCode = StatusCode.UserNameError;
            throw new UserException(statusCode);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.encode(password));//MD5加密
        userMapper.insertSelective(user);
        User userFormDB = userMapper.selectByUserName(username);
        return new UserDTO(userFormDB.getId(), userFormDB.getUsername(), userFormDB.getNickname(), userFormDB.getFileName(), userFormDB.getSign());
    }

    public UserDTO login(User user) throws BaseBusinessException {
        StatusCode statusCode;
        User userFormDB = userMapper.selectByUserName(user.getUsername());
        if (userFormDB == null) {
            statusCode = StatusCode.UserNameError;
            throw new UserException(statusCode);
        }
        if (!user.getPassword().equals(userFormDB.getPassword())) {//密码不匹配
            statusCode = StatusCode.PasswordError;
            throw new UserException(statusCode);
        }
        UserDTO userDto = new UserDTO(userFormDB.getId(), userFormDB.getUsername(), userFormDB.getNickname(), userFormDB.getFileName(), userFormDB.getSign());
        return userDto;
    }

    @Value("${systemParam.user.imagefile.path}")
    String pathname;

    public int updateImageByPrimaryKey(User record, MultipartFile file) {
        if (file.getOriginalFilename().length() < 80) {

        }
        File image = new File(pathname + file.getOriginalFilename());
        if (!image.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            image.getParentFile().mkdir();
            try {
                //创建文件
                image.createNewFile();
            } catch (IOException e) {
                log.error(e.toString() + "图片文件创建错误");
            }
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(image);
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

    public byte[] getImage(User user) {
        if (user == null) {
            log.error("请求的用户id不存在");
            byte[] bytes = {0};
            return bytes;//用户id不存在时候
        }
        String fileName = user.getFileName();
        if (fileName == "") {
            log.error("请求的用户的图片不存在");
            byte[] bytes = {0};
            return bytes;//图片不存在时候
        }
        File image = new File(fileName);
        FileInputStream is = null;
        byte[] imageByte = null;
        try {
            is = new FileInputStream(image);
            imageByte = new byte[is.available()];
            is.read(imageByte);
        } catch (FileNotFoundException e) {
            log.error(e.toString());
            throw new FileException(StatusCode.Fail);
        } catch (IOException e) {
            log.error(e.toString());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        return imageByte;
    }

    public StatusCode setInfo(String token, String nickname, String sign) {
        if (nickname == null & sign == null) {
            StatusCode statusCode = StatusCode.ParamFail;
            return statusCode;
        }
        User user = tokenUtil.getUser(token);
        user.setNickname(nickname);
        user.setSign(sign);
        userMapper.updateByPrimaryKeySelective(user);
        return StatusCode.Success;
    }

    public UserDTO getInfo(String token) {
        User user = tokenUtil.getUser(token);
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getNickname(), user.getFileName(), user.getSign());
        return userDTO;
    }
}
