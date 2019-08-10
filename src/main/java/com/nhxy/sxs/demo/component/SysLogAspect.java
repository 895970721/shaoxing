package com.nhxy.sxs.demo.component;

import com.nhxy.sxs.demo.component.Util.JoinPointUtil;
import com.nhxy.sxs.demo.entity.AccessLog;
import com.nhxy.sxs.demo.entity.User;
import com.nhxy.sxs.demo.mapper.AccessLogMapper;
import com.nhxy.sxs.demo.service.IpAddressServiceImpl;
import com.nhxy.sxs.demo.utils.IpUtil;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>Class: SysLogAspect</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/7 15:32
 */
@Component
@Aspect
@Order(1)
public class SysLogAspect {

    @Autowired
    UserTokenUtilImpl tokenUtil;
    AccessLogMapper accessLogMapper;
    @Autowired
    IpAddressServiceImpl ipAddressService;

    @Autowired
    public void setAccessLogMapper(AccessLogMapper accessLogMapper) {
        this.accessLogMapper = accessLogMapper;
    }

    @Pointcut("execution(* com.nhxy.sxs.demo.controller.*.*(..))")//controller包下所有类的所有方法
    public void pointcut() {
    }

    @After("pointcut()")
    public void after() {
    }

    @Before("pointcut()")
    public void before() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uA = request.getHeader("User-Agent");
        String ip = IpUtil.getIpAddress(request);
        Date time = new Date(System.currentTimeMillis());

        //取到参数中的token
        String token = JoinPointUtil.getTokenByJoinPont(joinPoint);
        User user;
        String userName;
        Integer userId;
        if (token != null) {
            user = tokenUtil.getUser(token);//因为这个切面的执行顺序比CheckTokenAspect晚,所以这里的token是可以信任的
            userName = user.getUsername();
            userId = user.getId();
        } else {
            userName = null;
            userId = null;
        }
        String method = joinPoint.getSignature().getName();

        AccessLog accessLog = new AccessLog();
        accessLog.setAccessTime(time);
        accessLog.setIp(ip);
        accessLog.setIpAddress(ipAddressService.queryAddress(ip));
        accessLog.setUa(uA);
        accessLog.setMethod(method);
        accessLog.setUserId(userId);
        accessLog.setUserName(userName);

        accessLogMapper.insertSelective(accessLog);
        Object result = joinPoint.proceed();
        return result;
    }
}
