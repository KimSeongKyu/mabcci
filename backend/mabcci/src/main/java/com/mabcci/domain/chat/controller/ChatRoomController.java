package com.mabcci.domain.chat.controller;

import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.ChatRoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomController(final ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @GetMapping("/chat/rooms")
    public List<ChatRoom> rooms() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

}
