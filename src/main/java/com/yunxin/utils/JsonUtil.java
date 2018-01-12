package com.yunxin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.yunxin.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 基于fastjson的json工具类
 */
public class JsonUtil {

    /**
     * 示例
     * java对象 转 json
     * @param object
     * @return
     */
    public static JSONObject javaToJson(Object object){
        return JSONObject.parseObject(JSON.toJSONString(object));
    }

    /**
     * 示例
     * json  转 java对象
     * @param jsonObject json对象
     * @return
     */
    public static <T> T jsonTojava(JSONObject jsonObject,Class<T> clazz){
        return JSONObject.toJavaObject(jsonObject,clazz);
    }

    /**
     * 示例
     * jsonArry 转list
     * @param jsonArray
     * @param clazz string.class,类
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(JSONArray jsonArray,Class<T> clazz){
        return JSONObject.parseArray(jsonArray.toJSONString(),clazz);
    }

    /**
     * 示例
     * list 转 json
     * @param list
     * @return
     */
    public static JSONArray listToJsonArry(List list){
        return JSON.parseArray(JSON.toJSONString(list));
    }


//  =============================================华丽分割线==========================

    /**
     * 根据msg是否为空，返回成功失败
     * @param msg
     * @return
     */
    public static JSONObject gen(String msg){
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(msg)){
            jsonObject.put("result",1);
        }else {
            jsonObject.put("result",0);
        }
        return jsonObject;
    }

    /**
     * result 为true 返回1，为false返回0
     * @param result
     * @return
     */
    public static JSONObject genBoolean(boolean result){
        JSONObject jsonObject = new JSONObject();
        if (result){
            jsonObject.put("result",1);
        }else {
            jsonObject.put("result",0);
        }
        return jsonObject;
    }

    public static JSONObject genSuccess(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",1);
        return jsonObject;
    }
    public static JSONObject genSuccess(List list){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",1);
        jsonObject.put("list",list);
        return jsonObject;
    }

    public static JSONObject genSuccess(Object object){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",1);
        jsonObject.put("object",object);
        return jsonObject;
    }

    public static JSONObject genError(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",0);
        return jsonObject;
    }


}
