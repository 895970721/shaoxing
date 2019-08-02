package com.nhxy.sxs.demo.component;

import com.nhxy.sxs.demo.enums.StatusCode;
import com.nhxy.sxs.demo.response.BaseResponse;
import com.nhxy.sxs.demo.utils.UserTokenUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Class: CheckTokenAspect</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/2 9:27
 */

@Aspect
@Component
@Slf4j
@Order(0)
public class CheckTokenAspect {

    @Autowired
    UserTokenUtilImpl tokenUtil;

    @Pointcut("@annotation(com.nhxy.sxs.demo.utils.CheckToken)")
    public void piontCut() {
    }

    @Before("piontCut()")
    public void before() {
    }

    @After("piontCut()")
    public void after() {
    }

    @Around("piontCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Object[] args = joinPoint.getArgs();
        //通过这获取到方法的所有参数名称的字符串数组
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        int tokenParamPoint = 0;//标识方法中的
        for (tokenParamPoint = 0; !parameterNames[tokenParamPoint].equals("token"); tokenParamPoint++) {
        }
        String token = (String) args[tokenParamPoint];
        System.out.println(token);
        if (!tokenUtil.verifie(token)) {
            log.debug("验证错误");
            return new BaseResponse(StatusCode.VeritfFail);
        }
        result = joinPoint.proceed();//方法执行。并且受到方法返回值
        return result;
    }

}
