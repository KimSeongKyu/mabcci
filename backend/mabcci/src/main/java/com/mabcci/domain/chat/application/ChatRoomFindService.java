package com.mabcci.domain.chat.application;

import com.mabcci.domain.chat.domain.*;
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

    // 왼쪽 채팅 리스트 표현 -> 이전에 채팅방을 만들어야 합니다.
    // 기존 채팅방이 존재하면? -> 다시 안 만들도록 해야하나
    // 사용자 얻고 -> 채팅에서 사용자 기준으로 -> 채팅 방 가져옴 -> dto에 담음
    // 채팅방 ID도 얻어야 할 것 같은데
    // 상대방ID + 상대방 picture + 속한 roomId
    public Set<ChattingRoomListResponse> findChatRoomByNickname(final Nickname nickname) {
        final Member member = findMemberByNickname(nickname);
        return chattingRepository.findByProposal(member).stream()
                .map(ChattingRoomListResponse::ofChatting)
                .collect(Collectors.toSet());
    }

    private Member findMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ChattingRoomResponse findByRoomId(final String roomId) {
        final ChatRoom chatRoom = findChattingRoomById(roomId);
        final Chatting chattings = chatRoom.chatting();
        final Set<ChatMessage> chatMessages = chatRoom.chattingMessages();
        return ChattingRoomResponse.fromChattings(chattings, chatMessages);
    }

    private ChatRoom findChattingRoomById(final String roomId) {
        return chatRoomRepository.findById(roomId).get();
    }

}
