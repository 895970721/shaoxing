package com.nhxy.sxs.demo.controller;

import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.enums.ExpTime;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.service.UserServiceImpl;
import com.nhxy.sxs.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    TokenUtil tokenUtil;

    @PostMapping("/register")
    public BaseResponse register(@RequestParam("user_name") String username,
                                 @RequestParam("pwd") String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.register(user);


    }

    @GetMapping("/login")
    public BaseResponse login(HttpServletResponse response, @RequestParam("user_name") String username,
                              @RequestParam("pwd") String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        BaseResponse baseResponse=userService.login(user);
        if(baseResponse.getCode()==1){
            Cookie cookie=new Cookie("token",tokenUtil.create(user, ExpTime.OneDay).getToken());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return baseResponse;
    }
}
