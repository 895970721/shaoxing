package com.nhxy.sxs.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileIOUtils {
    public static String getFileContent(String file_address, String FileName) {
        String FileContent = null;
        FileInputStream fis = null;
        InputStreamReader reader;
        BufferedReader bufferedReade;
        try {
            //当前读取的文件的字符集
            String encoding = "utf-8";
            //当前读取的字符
            int lineText;
            File file = new File(file_address + FileName);
            fis = new FileInputStream(file);
            //将文件输入字节流转成输入字符流
            reader = new InputStreamReader(fis, encoding);
            //包装该字符流具有缓冲区功能,提高效率
            bufferedReade = new BufferedReader(reader);
            //字符串拼接
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("\t\t\t\t");
            while ((lineText = bufferedReade.read()) != -1) {
                sBuilder.append((char) lineText);
            }
            FileContent = sBuilder.toString().replace(" ", "").replace("\n", "\n\t\t\t\t");
        } catch (FileNotFoundException e) {
            log.error("文件不存在或者文件不可读或者文件是目录");
        } catch (IOException e) {
            log.error("读取过程存在异常");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                log.error("文件关闭失败");
            }
        }
        return FileContent;
    }
}
