package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.Chatting;

import java.util.Set;
import java.util.stream.Collectors;

public class ChattingRoomListResponse {

    @JsonProperty("chatters")
    private Set<Chatter> chatters;

    public ChattingRoomListResponse(final ChatRoom chatRoom) {
        this(chatRoom.chattings().stream()
                .map(Chatting::member)
                .map(Chatter::new)
                .collect(Collectors.toSet()));
    }

    public ChattingRoomListResponse(final Set<Chatter> chatters) {
        this.chatters = chatters;
    }

}
