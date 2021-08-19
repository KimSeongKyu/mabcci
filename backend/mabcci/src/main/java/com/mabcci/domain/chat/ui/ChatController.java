package com.mabcci.domain.chat.ui;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.dto.ChattingMessage;
import com.mabcci.domain.chat.application.ChatSendService;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(originPatterns = "http://localhost:*")
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatSendService chatSendService;
    private final JwtUtil jwtUtil;

    public ChatController(final SimpMessageSendingOperations messagingTemplate,
                          final ChatSendService chatSendService,
                          final JwtUtil jwtUtil) {
        this.messagingTemplate = messagingTemplate;
        this.chatSendService = chatSendService;
        this.jwtUtil = jwtUtil;
    }

    @MessageMapping("/chat/message")
    public void message(ChattingMessage message, @Header("Authorization") String jwt) {
        final String nickname = jwtUtil.nickname(jwt);
        message.changeSender(nickname);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.roomId(), message);
        chatSendService.sendChatMessage(message);
    }

}
