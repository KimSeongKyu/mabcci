package com.mabcci.domain.chat.application;

import com.mabcci.domain.chat.domain.ChatMessage;
import com.mabcci.domain.chat.domain.ChatMessageRepository;
import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.ChatRoomRepository;
import com.mabcci.domain.chat.dto.ChattingMessage;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;

@Service
public class ChatSendService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public ChatSendService(final ChatMessageRepository chatMessageRepository,
                           final ChatRoomRepository chatRoomRepository,
                           final MemberRepository memberRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.memberRepository = memberRepository;
    }

    public void sendChatMessage(final ChattingMessage chattingMessage) {
        final ChatMessage chatMessage = mapToChatMessage(chattingMessage);
        chatMessageRepository.save(chatMessage);
    }

    private ChatMessage mapToChatMessage(final ChattingMessage chattingMessage) {
        final Nickname nickname = Nickname.of(chattingMessage.sender());
        final Member member = findMemberByNickname(nickname);
        final ChatRoom chatRoom = findChatRoomById(chattingMessage);
        return new ChatMessage(chattingMessage.message(), chatRoom, member);
    }

    private ChatRoom findChatRoomById(final ChattingMessage chattingMessage) {
        return chatRoomRepository.findById(chattingMessage.roomId())
                .orElseThrow(IllegalArgumentException::new);
    }

    private Member findMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}
