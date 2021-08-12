package com.mabcci.domain.chat.domain;

import com.mabcci.domain.chat.service.ChatService;
import lombok.Builder;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

// 클라이언트들의 정보를 가지고 있다.
// WebSocketSession 으로 부터 클라이언트의 정보를 얻으므로 이 리스트를 갖는다.
// 채팅방 식별번호랑 채팅방 이름을 주었다. -> UUIID로 전환 가능할지도?
// 입장후 첫 메시지 날리면 환영 메시지/이미 기존 존재시 메시지 보내면 메시지 보내기로 했다.
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(final String roomId, final String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(final WebSocketSession session, final ChatMessage chatMessage, ChatService chatService) {
        if(chatMessage.getType().equals(MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했씁니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    private <T> void sendMessage(T message, final ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

}
