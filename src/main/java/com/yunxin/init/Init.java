package com.yunxin.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger sysLogger = LoggerFactory.getLogger("init");

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        sysLogger.info("启动完后执行该方法");
    }
}
