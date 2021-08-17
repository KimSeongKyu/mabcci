package com.mabcci.domain.chat.model;

import java.io.Serializable;
import java.util.UUID;


public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;
    private long userCount; // 채팅방 인원수

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(final String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(final long userCount) {
        this.userCount = userCount;
    }

}
