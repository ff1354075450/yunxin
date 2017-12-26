package com.yunxin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.utils.OkhttpUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wechat")
public class WcTest {

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
    public Object getToken(String code, String state, Model model, HttpServletRequest request){
        JSONObject json = new JSONObject();
        json.put("get",code+":"+state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WxConfig.APPID
                +"&secret="+WxConfig.SECRET+"&code="+code
                +"&grant_type=authorization_code";
        String str = OkhttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(str);
        json.put("Token",jsonObject);
        str=getUserInfo(jsonObject.getString("access_token"),jsonObject.getString("openid"));
        JSONObject userinfo = JSON.parseObject(str);
        json.put("userInfo",userinfo);
        str=reflushToken(jsonObject.getString("refresh_token"));
        json.put("refreshToken",str);
        System.out.println(json.toJSONString());
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url2 = requestUri.substring(contextPath.length());
        model.addAttribute("wxConfig",WxConfig.sign(url2,null));
        return "share";
    }

    @RequestMapping("/share")
    public String getShare(Model model,HttpServletRequest request){
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url2 = requestUri.substring(contextPath.length());
        request.setAttribute("wxConfig",WxConfig.sign(url2,null));
        return "share";
    }

    private String reflushToken(String reflushToken){
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+WxConfig.APPID+"&grant_type=refresh_token&refresh_token="+reflushToken;
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
