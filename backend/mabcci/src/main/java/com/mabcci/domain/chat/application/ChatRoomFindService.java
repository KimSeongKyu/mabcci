package com.mabcci.domain.chat.application;

import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.ChatRoomRepository;
import com.mabcci.domain.chat.domain.Chatting;
import com.mabcci.domain.chat.domain.ChattingRepository;
import com.mabcci.domain.chat.dto.ChattingRoomListResponse;
import com.mabcci.domain.chat.dto.ChattingRoomResponse;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ChatRoomFindService {

    private final MemberRepository memberRepository;
    private final ChattingRepository chattingRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomFindService(final MemberRepository memberRepository,
                               final ChattingRepository chattingRepository,
                               final ChatRoomRepository chatRoomRepository) {
        this.memberRepository = memberRepository;
        this.chattingRepository = chattingRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public Set<ChattingRoomListResponse> findChatRoomByNickname(final Nickname nickname) {
        final Member member = findMemberByNickname(nickname);
        return chattingRepository.findByMember(member).stream()
                .map(Chatting::getChatRoom)
                .map(ChattingRoomListResponse::new)
                .collect(Collectors.toSet());
    }

    public ChattingRoomResponse findById(final String roomId) {
        final ChatRoom chatRoom = findChatRoomById(roomId);
        return new ChattingRoomResponse(chatRoom);
    }

    private Member findMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private ChatRoom findChatRoomById(final String roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
