package com.mabcci.domain.chat.controller;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.model.ChatMessage;
import com.mabcci.domain.chat.repo.ChatRoomRepository;
import com.mabcci.domain.chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    private final JwtUtil jwtUtil;


    public ChatController(final ChatRoomRepository chatRoomRepository,
                          final ChatService chatService, final JwtUtil jwtUtil) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatService = chatService;
        this.jwtUtil = jwtUtil;
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtUtil.nickname(token);
        message.setSender(nickname);
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        chatService.sendChatMessage(message);
    }

}
