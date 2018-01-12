package com.yunxin.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class TaskDemo {

    public static final Queue<DeferredResult<String>> respBodyQueue = new ConcurrentLinkedDeque<>();

    // @Scheduled(cron = "*/5 * * * * ? ") // 间隔5秒执行, cronExpression的配置说明
    public void taskCycle() {
        System.out.println("SpringMVC框架配置的定时任务");
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
