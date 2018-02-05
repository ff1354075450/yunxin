package com.yunxin.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class TaskUtil {
    /**
     * 任务队列,
     */
    public static BlockingDeque<Map<String,Object>> taskQueue = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap();
        map.put("function", new Task() {
            @Override
            public void listener(String name) {

            }
        });
    }


}
