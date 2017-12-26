package com.yunxin.api;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.utils.OkhttpUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

/**
 * 微信配置文件
 */
public class WxConfig {

    public final static String APPID="wxecb77a908ac21669";
    public final static String SECRET="75e7cb4a523fe9d098b169a2251ae390";

    private static String accessToken=null;
    private static String ticket=null;
    private static long ticketTime=0;


    public static void main(String[] args) {
        System.out.println(sign("http://www.dotwintech.com",new String[]{"choseImg"}));
    }

    /**
     * 获取微信默认签名
     * @return
     */
    public static String sign(String url,String[] apiList){
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String jsapi_ticket = "kgt8ON7yVITDhtdwci0qeWAaH_JaPtnIxZmV4ef8POqF5P9fGi0nsvi0kWgYYNYCZ3DZzNoi1LuiGhsi3IPw3w";
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp+
                "&url="+url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        String config;
        StringBuilder sb = new StringBuilder();
        sb.append("url:'").append(url).append("',\n");
        sb.append("jsapi_ticket:'").append(jsapi_ticket).append("',\n");
        sb.append("nonceStr:'").append(nonce_str).append("',\n");
        sb.append("timestamp:'").append(timestamp).append("',\n");
        sb.append("signature:'").append(signature).append("',\n");
        sb.append("jsApiList:[");
        if (apiList==null || apiList.length==0){
            sb.append("]\n");
            config=sb.toString();
        }else {
            for (String str:apiList){
                sb.append("'").append(str).append("',");
            }
            config=sb.substring(0,sb.length()-1) + "]\n";
        }
        return config;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    /**
     * 获取accessToken
     * @return
     */
    private static String getAccessToken() {
        String grant_type = "client_credential";//获取access_token填写client_credential
        //这个url链接地址和参数皆不能变
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+APPID+"&secret="+SECRET;
        String str = OkhttpUtil.get(url);
        System.out.println(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        accessToken = jsonObject.getString("access_token");
        return accessToken;
    }

    /**
     * 获取jsTicket
     * @return
     */
    private static String getTicket(){
        long nowTime = System.currentTimeMillis()/1000;
        if ((nowTime-ticketTime)<7200){
            return ticket;
        }
        ticketTime=nowTime;
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        String str = OkhttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject.toJSONString());
        String ticket = jsonObject.getString("ticket");
        return ticket;
    }


    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}
