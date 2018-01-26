package com.yunxin.websocket;

import com.yunxin.Config;
import com.yunxin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class HandlshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpSession session = ((ServletServerHttpRequest)request).getServletRequest().getSession(true);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (username!=null){
            attributes.put(Config.sessionUserName,username);
            return true;
        }else {
            logger.error("username is NULL");
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        logger.debug("afterHandshake");
    }
}
