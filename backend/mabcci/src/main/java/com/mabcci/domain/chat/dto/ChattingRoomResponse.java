package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.ChatMessage;
import com.mabcci.domain.chat.domain.Chatting;

import java.util.Set;
import java.util.stream.Collectors;

public class ChattingRoomResponse {

    @JsonProperty("chatters")
    private Set<Chatter> chatters;

    @JsonProperty("chattingMessages")
    private Set<ChattingMessage> chattingMessages;

    public static ChattingRoomResponse fromChattings(final Set<Chatting> chattings, final Set<ChatMessage> chatMessages) {
        final Set<Chatter> chatters = mapToChatterSet(chattings);
        final Set<ChattingMessage> chattingMessages = chatMessages.stream()
                .map(ChattingMessage::ofChatMessage)
                .collect(Collectors.toSet());
        return new ChattingRoomResponse(chatters, chattingMessages);
    }

    private static Set<Chatter> mapToChatterSet(final Set<Chatting> chattings) {
        return chattings.stream()
                .map(Chatting::member)
                .map(Chatter::new)
                .collect(Collectors.toSet());
    }

    public ChattingRoomResponse(final Set<Chatter> chatters, final Set<ChattingMessage> chattingMessages) {
        this.chatters = chatters;
        this.chattingMessages = chattingMessages;
    }

}
