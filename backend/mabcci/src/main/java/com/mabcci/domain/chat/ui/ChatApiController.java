package com.mabcci.domain.chat.ui;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.dto.ChattingRoomListResponse;
import com.mabcci.domain.chat.dto.ChattingRoomResponse;
import com.mabcci.domain.chat.application.ChatRoomFindService;
import com.mabcci.domain.chat.application.ChatRoomCreateService;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class ChatApiController {

    private ChatRoomFindService chatRoomFindService;
    private ChatRoomCreateService chatRoomCreateService;

    public ChatApiController(final ChatRoomFindService chatRoomFindService,
                             final ChatRoomCreateService chatRoomCreateService) {
        this.chatRoomFindService = chatRoomFindService;
        this.chatRoomCreateService = chatRoomCreateService;
    }

    @GetMapping("/chat/rooms/{nickname}")
    public ResponseEntity<Set<ChattingRoomListResponse>> rooms(@Valid @PathVariable Nickname nickname) {
        final Set<ChattingRoomListResponse> chatRoomListRespons = chatRoomFindService.findChatRoomByNickname(nickname);
        return ResponseEntity.ok().body(chatRoomListRespons);
    }

    @PostMapping("/chat/room")
    public ResponseEntity<String> createRoom(@Header("Authorization") String jwt, @Valid @RequestBody final Nickname mabcci) {
        final JwtUtil jwtUtil = new JwtUtil();
        final Nickname firstNickname = Nickname.of(jwtUtil.nickname(jwt));
        final String roomId = chatRoomCreateService.createChatting(firstNickname, mabcci);
        return ResponseEntity.ok().body(roomId);
    }

    // 상대방 이름 사진,
    @GetMapping("/chat/room/{roomId}")
    public ResponseEntity<ChattingRoomResponse> roomInfo(@PathVariable String roomId) {
        final ChattingRoomResponse chattingRoomResponse = chatRoomFindService.findById(roomId);
        return ResponseEntity.ok().body(chattingRoomResponse);
    }

}
