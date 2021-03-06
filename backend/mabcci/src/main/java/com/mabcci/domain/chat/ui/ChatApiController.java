package com.mabcci.domain.chat.ui;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.chat.dto.ChattingRoomCreateRequest;
import com.mabcci.domain.chat.dto.ChattingRoomListResponse;
import com.mabcci.domain.chat.dto.ChattingRoomResponse;
import com.mabcci.domain.chat.application.ChatRoomFindService;
import com.mabcci.domain.chat.application.ChatRoomCreateService;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(originPatterns = "*")
@RestController
public class ChatApiController {

    private final ChatRoomFindService chatRoomFindService;
    private final ChatRoomCreateService chatRoomCreateService;
    private final JwtUtil jwtUtil;

    public ChatApiController(final ChatRoomFindService chatRoomFindService, final ChatRoomCreateService chatRoomCreateService, final JwtUtil jwtUtil) {
        this.chatRoomFindService = chatRoomFindService;
        this.chatRoomCreateService = chatRoomCreateService;
        this.jwtUtil = jwtUtil;
    }

    // 내가 속한 채팅방 리스트를 가져온다.
    @GetMapping("/api/chat/rooms/{nickname}")
    public ResponseEntity<Set<ChattingRoomListResponse>> findChatRoomByNickname(@Valid @PathVariable Nickname nickname) {
        final Set<ChattingRoomListResponse> chatRoomListResponse = chatRoomFindService.findChatRoomByNickname(nickname);
        return ResponseEntity.ok().body(chatRoomListResponse);
    }

    // 채팅방을 만들고 roomId를 리턴한다 -> then 으로 다시 요청해서 접속해주십쇼
    @PostMapping("/api/chat/room")
    public ResponseEntity<String> createChattingRoom(@RequestHeader("Authorization") String jwt, @Valid @RequestBody final ChattingRoomCreateRequest request) {
        final Nickname proposal = Nickname.of(jwtUtil.nickname(jwt));
        final String roomId = chatRoomCreateService.createChattingRoom(proposal, request.nickname());
        return ResponseEntity.ok().body(roomId);
    }

    // 채팅방 자체에 뿌릴 데이터
    @GetMapping("/api/chat/room/{roomId}")
    public ResponseEntity<ChattingRoomResponse> findByRoomId(@PathVariable String roomId) {
        final ChattingRoomResponse chattingRoomResponse = chatRoomFindService.findByRoomId(roomId);
        return ResponseEntity.ok().body(chattingRoomResponse);
    }

}
