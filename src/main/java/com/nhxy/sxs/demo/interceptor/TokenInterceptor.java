package com.nhxy.sxs.demo.interceptor;

/**
 * <p>Class: TokenInterceptor</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 15:11
 */

import com.alibaba.fastjson.JSONObject;
import com.nhxy.sxs.demo.enums.ExpTime;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.utils.CheckToken;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 此拦截器通过{@link CheckToken}注解判断是否验证token<p></p>
 * 如果需要验证则验证
 * <p></p>
 * 如果验证成功和不需要验证，则返回true,如果验证失败<br>
 * 则返回false拒绝请求.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserTokenUtilImpl tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //取到映射到的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果此方法有checktoken注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            //如果require值为ture
            if (method.getAnnotation(CheckToken.class).required()) {
                // 从 cookie中取出 token
                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                    returnJson(response, 499, "您请先登录");
                    logger.debug("此连接没有cookie");
                    return false;
                }
                String token = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                    }
                }
                //如果没有token
                if (token == null) {
                    returnJson(response, 499, "您请先登录");
                    logger.debug("此连接有cookie但是没有token");
                    return false;
                }


                //如果token验证成功
                if (tokenUtil.verifie(token)) {
                    return true;
                } else {
                    if (tokenUtil.isExpire(token)) {
                        String newToken = tokenUtil.reSign(token, ExpTime.OneWeek);
                        if (newToken == null)
                            return false;
                        Cookie cookie = new Cookie("token", newToken);
                        cookie.setPath("/");
                        cookie.setHttpOnly(true);
                        cookie.setMaxAge(2592000);//过期一个月
                        response.addCookie(cookie);
                        logger.debug("续期token");
                        return true;
                    } else {
                        returnJson(response, 498, "您可能已经长久未登录，请先登录");
                        logger.debug("拒绝请求");
                        return false;
                    }
                }
            }
            //如果require值为false 则放行
            else
                return true;
        }
        //此方法没有checktoken注解
        else
            return true;
    }

    private void returnJson(HttpServletResponse response, int code, String msg) throws Exception {
        BaseResponse resultJson = new BaseResponse(code, msg);
        PrintWriter writer = null;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json , charset=utf-8");

        resultJson.setCode(code);
        resultJson.setMsg(msg);

        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(resultJson));
        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
