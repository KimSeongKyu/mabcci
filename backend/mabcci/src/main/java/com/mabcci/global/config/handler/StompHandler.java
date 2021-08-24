package com.mabcci.global.config.handler;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.domain.ChatRoomRepository;
import com.mabcci.domain.chat.application.ChatSendService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatSendService chatSendService;
    private final JwtUtil jwtUtil;

    public StompHandler(final ChatRoomRepository chatRoomRepository,
                        final ChatSendService chatSendService, final JwtUtil jwtUtil) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatSendService = chatSendService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        // 전략 패턴으로 돌리기 작전 준비
        if (StompCommand.CONNECT == accessor.getCommand()) {
        } if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
        } if (StompCommand.DISCONNECT == accessor.getCommand()) {
        }
        return message;
    }


}
