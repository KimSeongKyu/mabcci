package com.mabcci.domain.chat.model;

public class ChatMessage {

    public ChatMessage() {
    }

    public ChatMessage(final MessageType quit, final String roomId, final String name) {
        this(quit, roomId, name, null, 0L);
    }

    public ChatMessage(MessageType type, String roomId, String sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private long userCount; // 채팅방 인원수, 채팅방 내에서 메시지가 전달될때 인원수 갱신시 사용


    public MessageType getType() {
        return type;
    }

    public void setType(final MessageType type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(final String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(final long userCount) {
        this.userCount = userCount;
    }
}
