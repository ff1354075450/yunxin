package com.yunxin.websocket;

import com.alibaba.fastjson.JSON;
import com.yunxin.Config;
import com.yunxin.entity.User;
import com.yunxin.entity.WebSocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;

@Component
public class MsgHandler extends AbstractWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("sessionid:"+session.getId());
        logger.info("getLocalAddress:" + session.getLocalAddress().toString());
        logger.info("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
        logger.info("getUri:" + session.getUri().toString());
        logger.info("getPrincipal:" + session.getPrincipal());
        Map<String,Object> attributes = session.getAttributes();
        User user = (User) attributes.get(Config.sessionUserName);
        logger.info("user:"+user.toString());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.debug("handlermsg:"+message.toString());
        String msgStr = message.getPayload().toString();
        if(msgStr.indexOf("java.nio.HeapByteBuffer")>-1){
            return;
        }
//        将json转为对象
        WebSocketMsg msg = JSON.parseObject(msgStr,WebSocketMsg.class);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("消息传输出错");
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("连接断开 status:"+status);
        super.afterConnectionClosed(session, status);
    }
}
