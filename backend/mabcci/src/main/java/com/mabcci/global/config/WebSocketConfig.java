package com.mabcci.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    public WebSocketConfig(final WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    // 만들었던 웹 소켓 핸들러를 등록한다.
    // 레지스트리에는 이미 여러 핸들러가 있고 우리가 작성한 핸들러를 목록에 추가하는 일이다.
    // 멀티벨류맵으로 이루어져 있으며, 객체가 키 패스가 벨류이다.
    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        // 웹소켓 핸들러와, 엔드포인트 입력
        // CORS는 *로 지정을 한다.
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }

}
