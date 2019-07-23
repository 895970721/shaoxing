package com.nhxy.sxs.demo.utils;

public class StringUtils {
    public static String StringToArrayGetContent(String str,int index,int size){
        //以逗号为分割符,转换为字符串数组
        String[] split = str.split(",");
        //截取第三个元素,从第十个字符读取,获取文件名
        String Content = split[index].substring(size).trim();
        return Content;
    }
}
