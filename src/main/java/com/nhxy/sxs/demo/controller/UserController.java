package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.TokenEntity;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.ExpTime;
import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.UserServiceImpl;
import com.nhxy.sxs.demo.utils.Base64Util;
import com.nhxy.sxs.demo.utils.CheckToken;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Class: UserController</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 16:35
 */

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserTokenUtilImpl tokenUtil;

    @PostMapping("/register")
    /**
     *
     * @param username 注册的用户名
     * @param password 用base64编码的密码
     */
    public BaseResponse register(@RequestParam("user_name") String username,
                                 @RequestParam("pwd") String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(Base64Util.decode(password));
        return userService.register(user);


    }

    /**
     * @param username 登录的用户名
     * @param password 用base64编码的密码
     */
    @PostMapping("/login")
    public BaseResponse login(HttpServletResponse response, @RequestParam("user_name") String username,
                              @RequestParam("pwd") String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(Base64Util.decode(password));
        BaseResponse baseResponse = userService.login(user);
        if (baseResponse.getCode() == 1) {//创建token 封装进cookie；
            Cookie cookie = new Cookie("token", tokenUtil.create(user, ExpTime.OneDay).getToken());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return baseResponse;
    }

    @CheckToken(type = CheckToken.user_tpye)
    @PostMapping("/uploadimage")
    public BaseResponse uploadImage(@RequestParam() MultipartFile file, @CookieValue("token") Cookie token) {
        BaseResponse response;
        TokenEntity tokenEntity = tokenUtil.getTokenEntity(token.getValue());
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
     * @param token
     * @return
     */
    @CheckToken
    @GetMapping(value = "/getpicture", produces = "image/png")
    public byte[] getImage(@CookieValue("token") Cookie token) {
        User user = tokenUtil.getUser(token.getValue());
        return userService.getImage(user);
    }

    /**
     * 通过userid返回接口
     * @param userId
     * @return
     */
    @GetMapping(value = "/getpicture/{user_id}", produces = "image/png")
    public byte[] getImage(@PathVariable("user_id") Integer userId) {
        User user = userService.selectByPrimaryKey(userId);
        return userService.getImage(user);
    }
}
