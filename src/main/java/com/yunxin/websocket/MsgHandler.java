package com.yunxin.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.Config;
import com.yunxin.entity.User;
import com.yunxin.entity.WebSocketMsg;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MsgHandler extends AbstractWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static ConcurrentHashMap<String,WebSocketSession> userSession = new ConcurrentHashMap<String, WebSocketSession>();

    //未读信息缓存
    private Cache<String,List<WebSocketMsg>> unreadMsg;

    private static Lock lock = new ReentrantLock();

    public void setUnreadMsg(Cache<String,List<WebSocketMsg>> unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //连接成功
        logger.info("sessionid:"+session.getId());
        logger.info("getLocalAddress:" + session.getLocalAddress().toString());
        logger.info("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
        logger.info("getUri:" + session.getUri().toString());
        logger.info("getPrincipal:" + session.getPrincipal());
        Map<String,Object> attributes = session.getAttributes();
        String username = (String) attributes.get(Config.sessionUserName);
        logger.info("user:"+username);
        userSession.put(username,session);

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
        msg.setSender(getUser(session));


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
        if (session.isOpen()){
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("连接断开 status:"+status);
        super.afterConnectionClosed(session, status);
    }

    /**
     * 从session中获取用户名
     * @param session
     * @return
     */
    private String getUser(WebSocketSession session){
        Map<String,Object> attributes = session.getAttributes();
        String username = (String) attributes.get(Config.sessionUserName);
        return username;
    }

    private void sendMsg(WebSocketMsg msg){
        String reveiver = msg.getReceiver();
        msg.setTime((int) (System.currentTimeMillis()/1000));
        WebSocketSession session = userSession.get(reveiver);
        TextMessage message = new TextMessage(JSON.toJSONString(msg));
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //用户不在线，保存未读信息到缓存中
            List<WebSocketMsg> msgs = unreadMsg.get(reveiver);
            lock.lock();//加锁
            if (msgs ==null){
                msgs = new ArrayList<>();
            }
            msgs.add(msg);
            lock.unlock();//解锁
        }
    }

    /**
     * 接收未读消息
     * @param session
     */
    private void receiveMsg(WebSocketSession session){
        String username = getUser(session);
        List<WebSocketMsg> msgs = unreadMsg.get(username);
        lock.lock();
        for (WebSocketMsg msg:msgs){
            TextMessage message = new TextMessage(JSON.toJSONString(msg));
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
