package com.yunxin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.utils.OkhttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WcTest {

   private static String APPID="wxecb77a908ac21669";
   private static String SECRET="75e7cb4a523fe9d098b169a2251ae390";

   private static String access_token=null;

    /**
     * 微信服务器验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping("/checkSignature")
    public Object checkSignature(String signature,Long timestamp,String nonce,String echostr){
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        return echostr;
    }

    @RequestMapping("/getToken")
    public Object getToken(String code,String state){
        JSONObject json = new JSONObject();
        json.put("get",code+":"+state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID
                +"&secret="+SECRET+"&code="+code
                +"&grant_type=authorization_code";
        String str = OkhttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(str);
        json.put("Token",jsonObject);
        str=getUserInfo(jsonObject.getString("access_token"),jsonObject.getString("openid"));
        JSONObject userinfo = JSON.parseObject(str);
        json.put("userInfo",userinfo);
        str=reflushToken(jsonObject.getString("refresh_token"));
        json.put("refreshToken",str);
        return json;
    }

    private String reflushToken(String reflushToken){
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+reflushToken;
        String str = OkhttpUtil.get(url);
        System.out.println(str);
        return str;
    }

    private String getUserInfo(String access_token,String openId){
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
        String str = OkhttpUtil.get(url);
        System.out.println(str);
        return str;
    }
}
