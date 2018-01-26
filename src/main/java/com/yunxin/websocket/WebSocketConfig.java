package com.yunxin.websocket;

import com.yunxin.entity.WebSocketMsg;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.standard.WebSocketContainerFactoryBean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;

/**
 * 该请求不能被拦截器拦击
 * configuration 表示用一个类来代替xml来配置bean容器
 *
 */

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    private CacheManager cacheManager;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	    //允许跨域
        registry.addHandler(systemWebSocketHandler(),"/websocket").setAllowedOrigins("*").addInterceptors(new HandlshakeInterceptor());
        registry.addHandler(systemWebSocketHandler(),"/websocket/sockjs").setAllowedOrigins("*").addInterceptors(new HandlshakeInterceptor()).withSockJS();
    }
	
	@Bean
    public WebSocketHandler systemWebSocketHandler(){
        Cache<String,List<WebSocketMsg>> unreadMsg=cacheManager.getCache("unread_msgs");
        MsgHandler msgHandler = new MsgHandler();//注入缓存
        msgHandler.setUnreadMsg(unreadMsg);
        return msgHandler;
    }
	
    @Bean
    public WebSocketContainerFactoryBean createWebSocketContainer() {
        WebSocketContainerFactoryBean container = new WebSocketContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}
