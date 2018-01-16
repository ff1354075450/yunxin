package com.yunxin.entity;

public class WebSocketMsg {

    /**
     * 发送人id
     */
    private Long senderId;

    /**
     * 接收人id
     */
    private Long receiverId;

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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", chatRoomId=" + chatRoomId +
                '}';
    }
}
