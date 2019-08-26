package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.dto.UserDTO;
import com.nhxy.sxs.demo.entity.TokenEntity;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.ExpTime;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.exception.BaseBusinessException;
import com.nhxy.sxs.demo.exception.UserException;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.UserServiceImpl;
import com.nhxy.sxs.demo.utils.Base64Util;
import com.nhxy.sxs.demo.utils.CheckToken;
import com.nhxy.sxs.demo.utils.MD5Util;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Class: UserController</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 16:35
 */

@RestController()
@RequestMapping("/user")
@Slf4j
@Api(value = "user", tags = "用户操作接口")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserTokenUtilImpl tokenUtil;

    @PostMapping("/register")
    @ApiOperation(httpMethod = "POST", value = "注册用户")
    /**
     *
     * @param username 注册的用户名
     * @param password 用base64编码的密码
     */
    public BaseResponse register(@RequestParam("user_name") String username,
                                 @RequestParam("pwd") String password) {
        try {
            UserDTO user = userService.register(username, Base64Util.decode(password));

            Map data = new LinkedHashMap();
            data.put("usr", user);
            BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
            baseResponse.setData(data);
            return baseResponse;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Base64解密错误:" + e.getMessage());
            throw new BaseBusinessException(StatusCode.PasswordError);
        }
    }

    /**
     * @param username 登录的用户名
     * @param password 用base64编码的密码
     */
    @PostMapping("/login")
    @ApiOperation(httpMethod = "POST", value = "用户登录，返回token")
    public BaseResponse login(@RequestParam("user_name") String username,
                              @RequestParam("pwd") String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(MD5Util.encode(Base64Util.decode(password)));
            UserDTO userDTO = userService.login(user);
            //token
            String token = tokenUtil.create(user, ExpTime.OneDay).getToken();
            BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
            Map data = new LinkedHashMap();
            data.put("token", token);
            data.put("usr", userDTO);
            baseResponse.setData(data);
            return baseResponse;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Base64解密错误: " + e.getMessage());
            throw new BaseBusinessException(StatusCode.PasswordError);
        }
    }

    @CheckToken(type = CheckToken.user_tpye)
    @PostMapping("/uploadimage")
    @ApiOperation(httpMethod = "POST", value = "上传用户头像")
    public BaseResponse uploadImage(@RequestParam() MultipartFile file, @RequestParam("token") String token) {

        BaseResponse response;
        TokenEntity tokenEntity = tokenUtil.getTokenEntity(token);
        User user = userService.selectByUserName(tokenEntity.getUsername());
        if (userService.updateImageByPrimaryKey(user, file) == 1) {
            response = new BaseResponse(StatusCode.Success);
            response.setMsg("图片上传成功");
            return response;
        } else {
            return new BaseResponse(StatusCode.Fail);
        }
    }

    /**
     * 通过用户携带的token返回头像
     * 推荐个人中心使用此接口
     *
     * @param token
     * @return
     */
    @CheckToken
    @GetMapping(value = "/getpicture", produces = "image/png")
    @ApiOperation(httpMethod = "GET", value = "通过token返回头像")
    public byte[] getImage(@RequestParam("token") String token) {
        User user = tokenUtil.getUser(token);
        return userService.getImage(user);
    }

    /**
     * <p>通过userid返回接口</p>
     * <p>如果userid不存在或者对应图片不存在会返回一个{0}的byte数组</p>
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/getpicture/{user_id}", produces = "image/png")
    @ApiOperation(httpMethod = "GET", value = "通过userid返回用户头像")
    public byte[] getImage(@PathVariable("user_id") Integer userId) {

        User user = userService.selectByPrimaryKey(userId);
        return userService.getImage(user);
    }

    @PostMapping("/setinfo")
    @CheckToken
    public BaseResponse setInfo(@RequestParam("token") String token, @RequestParam(value = "nickname", required = false) String nickname,
                                @RequestParam(value = "sgin", required = false) String sign) {
        StatusCode statusCode = userService.setInfo(token, nickname, sign);
        return new BaseResponse(statusCode);
    }

    @GetMapping("/getInfo")
    @CheckToken
    public BaseResponse getInfo(@RequestParam("token") String token) {
        UserDTO userDTO = userService.getInfo(token);
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(userDTO);
        return baseResponse;
    }
    @PostMapping("/refresh")
    @ApiOperation("刷新token")
    public BaseResponse refresh(@ApiParam("旧token") @RequestParam("token") String token){
        String newToken=tokenUtil.reSign(token,ExpTime.OneDay);
        if (newToken==null){
            throw new UserException(StatusCode.ResignFail);
        }
        Map result=new HashMap();
        result.put("token",newToken);
        BaseResponse baseResponse=new BaseResponse(StatusCode.Success);
        baseResponse.setData(result);
        return baseResponse;
    }
}
