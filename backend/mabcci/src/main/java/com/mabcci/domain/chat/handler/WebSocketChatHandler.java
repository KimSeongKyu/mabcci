package com.mabcci.domain.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketChatHandler extends TextWebSocketHandler { // 핸들러를 상속 받으면?

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // WebSocketChatHandler 은 서버(1): 클라이언트(N)의 요청을 처리하는 핸들러이다.
    // 즉 여러 클라이언트의 요청을 처리받고 응답을 주는 핸들러다.
    // 현재 코드는 클라이언트로부터 메시지를 받으면 이를 로그로 찍고 클라이언트에게 환영 메시지를 보내는 역할을 한다.
    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        final String payload = message.getPayload();
        logger.info(payload);
        TextMessage textMessage = new TextMessage("Mabcci 채팅 서버에 오신 것을 환영합니다.");
        session.sendMessage(textMessage);
    }

}
