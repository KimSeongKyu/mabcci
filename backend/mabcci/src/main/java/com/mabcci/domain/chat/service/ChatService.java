package com.mabcci.domain.chat.service;

import com.mabcci.domain.chat.model.ChatMessage;
import com.mabcci.domain.chat.repo.ChatRoomRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import static com.mabcci.domain.chat.model.MessageType.ENTER;
import static com.mabcci.domain.chat.model.MessageType.QUIT;

@Service
public class ChatService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatRoomRepository chatRoomRepository;

    public ChatService(final ChannelTopic channelTopic, final RedisTemplate redisTemplate, final ChatRoomRepository chatRoomRepository) {
        this.channelTopic = channelTopic;
        this.redisTemplate = redisTemplate;
        this.chatRoomRepository = chatRoomRepository;
    }

    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    public void sendChatMessage(ChatMessage chatMessage) {
        chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
        if (ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에 입장했습니다.");
            chatMessage.setSender("[알림]");
        }
        if (QUIT.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

}
