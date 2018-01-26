package com.yunxin.entity;

public class WebSocketMsg {

    /**
     * 发送人id
     */
    private String  sender;

    /**
     * 接收人id
     */
    private String receiver;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间戳
     */
    private Integer time;

    /**
     * 聊天房间id
     */
    private Long chatRoomId;

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WebSocketMsg{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", chatRoomId=" + chatRoomId +
                '}';
    }
}
