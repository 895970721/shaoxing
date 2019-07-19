package com.nhxy.sxs.demo.service;

import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.mapper.UserMapper;
import com.nhxy.sxs.demo.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>Class: UserServiceImpl</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/18 18:27
 */
@Service
public class UserServiceImpl implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Value("${systemParam.user.name_min_length}")
    int usernameMinLength;
    @Value("${systemParam.user.password_length}")
    int passwordLength;


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
        if (user.getPassword().length() != passwordLength) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("密码长度错误");
            return baseResponse;
        }
        if (userMapper.selectByUserName(user.getUsername()) != null) {
            baseResponse = new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("用户名重复，请重试");
            return baseResponse;
        }
        userMapper.insertSelective(user);
        return new BaseResponse(StatusCode.Success);
    }
    public BaseResponse login(User user){
        BaseResponse baseResponse;
        User userFormDB =userMapper.selectByUserName(user.getUsername());
        if(userFormDB==null){
            baseResponse=new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("用户名错误");
            return baseResponse;
        }
        if(!user.getPassword().equals(userFormDB.getPassword())){//密码不匹配
            baseResponse=new BaseResponse(StatusCode.Fail);
            baseResponse.setMsg("密码错误");
            return baseResponse;
        }
        return new BaseResponse(StatusCode.Success);
    }
}
