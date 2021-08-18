package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.ChatMessage;

public class ChattingMessage {

    @JsonProperty("roomId")
    private String roomId;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("message")
    private String message;

    private ChattingMessage() {
    }

    public static ChattingMessage ofChatMessage(final ChatMessage chatMessage) {
        final String id = chatMessage.chatRoom().id();
        final String nickname = chatMessage.member().nickname().nickname();
        final String message = chatMessage.message();
        return new ChattingMessage(id, nickname, message);
    }

    private ChattingMessage(final String roomId, final String sender, final String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public String roomId() {
        return roomId;
    }

    public String sender() {
        return sender;
    }

    public String message() {
        return message;
    }

    public void changeSender(final String sender) {
        this.sender = sender;
    }

}
