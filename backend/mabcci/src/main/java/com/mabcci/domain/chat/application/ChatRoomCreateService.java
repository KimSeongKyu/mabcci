package com.mabcci.domain.chat.application;

import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.Chatting;
import com.mabcci.domain.chat.domain.ChattingRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomCreateService {

    private final ChattingRepository chattingRepository;
    private final MemberRepository memberRepository;

    public ChatRoomCreateService(final ChattingRepository chattingRepository, final MemberRepository memberRepository) {
        this.chattingRepository = chattingRepository;
        this.memberRepository = memberRepository;
    }

    // 기존 채팅방 있으면 그대로 돌려줌 -> 없으면 생성
    public String createChattingRoom(final Nickname proposal, final Nickname mabcci) {
        final ChatRoom chatRoom = new ChatRoom();
        final Member proposalMember = findMemberByNickName(proposal);
        final Member mabcciMember = findMemberByNickName(mabcci);
        if (!isChatRoomExist(proposalMember, mabcciMember)) {
            chattingRepository.save(new Chatting(proposalMember, chatRoom));
            chattingRepository.save(new Chatting(mabcciMember, chatRoom));
            return chatRoom.id();
        }
        return existedChatRoomId(proposalMember, mabcciMember);
    }

    private String existedChatRoomId(final Member proposalMember, final Member mabcciMember) {
        return chattingRepository.findByMember(mabcciMember).stream()
                .filter(chatting -> chatting.isContainSameMember(proposalMember))
                .map(Chatting::chatRoom)
                .map(ChatRoom::id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private Boolean isChatRoomExist(final Member proposalMember, final Member mabcciMember) {
        return chattingRepository.findByMember(mabcciMember).stream()
                .filter(chatting -> chatting.isContainSameMember(proposalMember))
                .map(chatting -> chatting.isContainSameMember(proposalMember))
                .findFirst()
                .orElse(false);
    }

    private Member findMemberByNickName(final Nickname firstNickname) {
        return memberRepository.findByNickName(firstNickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}

