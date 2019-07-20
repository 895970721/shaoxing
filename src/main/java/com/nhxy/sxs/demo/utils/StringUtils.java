package com.nhxy.sxs.demo.utils;

public class StringUtils {
    public static String GetFileName(String str){
        //以逗号为分割符,转换为字符串数组
        String[] split = str.split(",");
        //截取第三个元素,从第十个字符读取,获取文件名
        String FileName = split[2].substring(10);
        return FileName;
    }
}
