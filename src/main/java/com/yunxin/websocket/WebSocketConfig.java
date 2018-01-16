package com.yunxin.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.standard.WebSocketContainerFactoryBean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 该请求不能被拦截器拦击
 */

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	    //允许跨域
        registry.addHandler(systemWebSocketHandler(),"/websocket").setAllowedOrigins("*").addInterceptors(new HandlshakeInterceptor());
        registry.addHandler(systemWebSocketHandler(),"/websocket/sockjs").setAllowedOrigins("*").addInterceptors(new HandlshakeInterceptor()).withSockJS();
    }
	
	@Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new MsgHandler();
    }
	
    @Bean
    public WebSocketContainerFactoryBean createWebSocketContainer() {
        WebSocketContainerFactoryBean container = new WebSocketContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}
