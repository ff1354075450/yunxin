package com.yunxin.utils;

import java.util.Random;

/**
 * @Author ff
 * @Data 2017/11/3 14:55
 */
public class RandomUtil {
    /**
     * 生成一个随机字符串
     * @param length 长度
     * @param seed 随机数种子
     * @return
     */
    public static String getRandomString(int length,Long seed) { //length表示生成字符串的长度
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        String base = "0123456789";
        Random random = new Random(seed);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
