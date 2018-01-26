package com.yunxin.api.wx;

import com.yunxin.utils.MD5Util;
import org.omg.CORBA.OBJ_ADAPTER;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信签名测试类
 */
public class SignalTest {

    //计算签名
    public static void main(String[] args) {
        Map<String,Object> data = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        data.put("appid","wxd930ea5d5a258f4f");
        data.put("mch_id",10000100);
        data.put("device_info",1000);
        data.put("body","test");
        data.put("nonce_str","ibuaiVcKdpRxkhJA");

        Iterator iterator = data.keySet().iterator();
        String stringA = "";
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            Object value = data.get(key);
            stringA += key+"="+value+"&";
        };
        stringA += "key=192006250b4c09247ec02edce69f6a2d";
        System.out.println(stringA);
        String md5 = MD5Util.get(stringA);
        System.out.println(md5);
    }





}
