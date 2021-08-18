package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.MessageType;

public class ChattingMessage {

    @JsonProperty("messageType")
    private MessageType messageType;

    @JsonProperty("roomId")
    private String roomId;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("message")
    private String message;

    private ChattingMessage() {
    }

    public ChattingMessage(final MessageType messageType, final String roomId,
                           final String sender, final String message) {
        this.messageType = messageType;
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

    public MessageType messageType() {
        return messageType;
    }

    public void changeSender(final String sender) {
        this.sender = sender;
    }

}
