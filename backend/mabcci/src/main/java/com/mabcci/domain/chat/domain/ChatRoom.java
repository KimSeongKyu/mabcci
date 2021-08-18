package com.mabcci.domain.chat.domain;

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

    public ChatRoom() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Set<Chatting> getChattings() {
        return chattings;
    }

    public void addAllChatting(final Set<Chatting> chattings) {
        this.chattings.addAll(chattings);
        chattings.stream().forEach(chatting -> chatting.addChatRoom(this));
    }

}
