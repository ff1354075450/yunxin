package com.yunxin.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Util {

    public static String get(String key){
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(key.getBytes("utf-8"));//md5加密
        }catch (Exception e){
            bytes = key.getBytes();
            e.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        key = base64Encoder.encode(bytes);//base64编码
        return key;
    }

    public static void main(String[] args) {
        System.out.println("args"+get("xxxiefa;ldf"));
    }
}
