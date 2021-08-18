package com.mabcci.domain.chat.domain;

import com.mabcci.domain.chat.dto.ChattingMessage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class ChatRoom {

    @Id
    @Column(name = "chat_room_id")
    private String id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Chatting> chattings = new HashSet<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatMessage> chattingMessages = new HashSet<>();

    public ChatRoom() {
        this.id = UUID.randomUUID().toString();
    }

    public String id() {
        return id;
    }

    public Set<Chatting> chattings() {
        return chattings;
    }

    public Set<ChatMessage> chattingMessages() {
        return chattingMessages;
    }

}
