package com.nhxy.sxs.demo.component.Util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * <p>Class: JoinPointUtil</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/8/8 11:15
 */
public class JoinPointUtil {
    /**
     * 用于封装从joinpoint获取token的操作
     *
     * @param joinPoint
     * @return
     */
    public static String getTokenByJoinPont(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        int tokenParamPoint = 0;//标识token这个参数在参数数组里的位置
        for (tokenParamPoint = 0;
             !paramNames[tokenParamPoint].equals("token") && paramNames.length - 1 > tokenParamPoint;//注意 长度从1开始 数组下标是从0开始
             tokenParamPoint++) {
        }
        String token;
        if (!paramNames[tokenParamPoint].equals("token")) {//如果停止循环的时候 指针所指的依旧不是token则token=null
            return null;
        }
        token = (String) args[tokenParamPoint];
        return token;
    }
}
