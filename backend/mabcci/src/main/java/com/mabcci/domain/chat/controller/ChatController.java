package com.mabcci.domain.chat.controller;

import com.mabcci.domain.chat.domain.ChatMessage;
import com.mabcci.domain.chat.domain.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    public ChatController(final SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if(MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+ "님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }

}
