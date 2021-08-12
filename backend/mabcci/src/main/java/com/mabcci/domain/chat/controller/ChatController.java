package com.mabcci.domain.chat.controller;

import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping("/chat")
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

}
