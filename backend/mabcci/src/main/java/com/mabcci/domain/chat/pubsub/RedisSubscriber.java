package com.mabcci.domain.chat.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.chat.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    public RedisSubscriber(final ObjectMapper objectMapper, final SimpMessageSendingOperations messagingTemplate) {
        this.objectMapper = objectMapper;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String publishMessage) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}
