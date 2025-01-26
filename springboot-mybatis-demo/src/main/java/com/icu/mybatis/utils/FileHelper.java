package com.icu.mybatis.utils;

public class FileHelper {
    public static String getExtName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getUniqueFileName(String fileName){
        return CommonHelper.getUniqueId() + "." + getExtName(fileName);
    }
}
