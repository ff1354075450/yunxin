package com.yunxin.task;

import com.yunxin.entity.WebSocketMsg;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class TaskDemo {

    public static final Queue<DeferredResult<String>> respBodyQueue = new ConcurrentLinkedDeque<>();

    @Autowired
    private CacheManager cacheManager;

    private Integer outTime=3600;//缓存超时时间



//     @Scheduled(cron = "*/5 * * * * ? ") // 间隔5秒执行, cronExpression的配置说明
    public void taskCycle() {
        System.out.println("SpringMVC框架配置的定时任务");
    }

//    每天3点触发,清除超时缓存
    @Scheduled(cron = "0 0 3 * * ?")
    public void clearCache() {
        Cache<String, List<WebSocketMsg>> unreadMsg = cacheManager.getCache("unread_msgs");
        for (String str : unreadMsg.keys()) {
            List<WebSocketMsg> list = unreadMsg.get(str);

            if (list != null && list.size() > 1) {
                for (int i = 0; i < list.size(); i++) {
                    WebSocketMsg msg = list.get(i);
                    Integer time = msg.getTime();
                    if ((System.currentTimeMillis() / 1000) - time > outTime) {//去除超时消息
                        list.remove(i);
                    }
                }
            }

            if(list == null || list.size() < 1){//删除空缓存
                unreadMsg.remove(str);
            }
        }
    }


    @Scheduled(fixedRate=2000)
    public void processQueues() {
        for (DeferredResult<String> result : respBodyQueue) {
            System.out.println("processQueues");
            result.setResult("Deferred result");
            respBodyQueue.remove(result);
        }
    }
}
