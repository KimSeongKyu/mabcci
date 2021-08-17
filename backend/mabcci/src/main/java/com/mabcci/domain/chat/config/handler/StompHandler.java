package com.mabcci.domain.chat.config.handler;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.model.ChatMessage;
import com.mabcci.domain.chat.repo.ChatRoomRepository;
import com.mabcci.domain.chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.Optional;

import static com.mabcci.domain.chat.model.MessageType.*;
import static org.springframework.messaging.simp.stomp.StompCommand.*;

@Component
public class StompHandler implements ChannelInterceptor {

    public static final int BEARER_EXCLUDE_BEGIN_INDEX = 6;
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    private final JwtUtil jwtUtil;

    public StompHandler(final ChatRoomRepository chatRoomRepository,
                        final ChatService chatService, final JwtUtil jwtUtil) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatService = chatService;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        final StompCommand command = accessor.getCommand();
        log.error(accessor.getCommand().toString());
        if (CONNECT == accessor.getCommand()) {
            final String accessToken = accessor.getFirstNativeHeader("Authorization");
            final String nickname = mapToNickname(accessToken);
            log.info("CONNECT {}", nickname);
        }
        else if (SUBSCRIBE == command) { // 채팅룸 구독요청
            String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            chatRoomRepository.setUserEnterInfo(sessionId, roomId);
            chatRoomRepository.plusUserCount(roomId);
            String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser")).map(Principal::getName).orElse("UnknownUser");
            chatService.sendChatMessage(new ChatMessage(ENTER, roomId, name));
            log.info("SUBSCRIBED {}, {}", name, roomId);
        }
        else if (DISCONNECT == command) { // Websocket 연결 종료
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            String roomId = chatRoomRepository.getUserEnterRoomId(sessionId);
            chatRoomRepository.minusUserCount(roomId);
            String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser")).map(Principal::getName).orElse("UnknownUser");
            chatService.sendChatMessage(new ChatMessage(QUIT, roomId, name));
            chatRoomRepository.removeUserEnterInfo(sessionId);
            log.info("DISCONNECTED {}, {}", sessionId, roomId);
        }
        return message;
    }

    private String mapToNickname(String accessToken) {
        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer")) {
            accessToken = accessToken.substring(BEARER_EXCLUDE_BEGIN_INDEX);
        }
        return jwtUtil.nickname(accessToken);
    }

}
