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

    public String createChattingRoom(final Nickname proposal, final Nickname mabcci) {
        final ChatRoom chatRoom = new ChatRoom();
        final Member proposalMember = findMemberByNickName(proposal);
        final Member mabcciMember = findMemberByNickName(mabcci);
        if (isChatRoomExist(proposalMember, mabcciMember)) {
            return existedChatRoomId(proposalMember, mabcciMember);
        }
        chattingRepository.save(new Chatting(proposalMember, mabcciMember, chatRoom));
        return chatRoom.id();
    }

    private String existedChatRoomId(final Member proposalMember, final Member mabcciMember) {
        final Chatting chatting = chattingRepository.findByProposalAndMabcci(proposalMember, mabcciMember)
                .orElseThrow(IllegalArgumentException::new);
        final ChatRoom chatRoom = chatting.chatRoom();
        return chatRoom.id();
    }

    private Boolean isChatRoomExist(final Member proposalMember, final Member mabcciMember) {
        return chattingRepository.findByProposalAndMabcci(proposalMember, mabcciMember).isPresent();
    }

    private Member findMemberByNickName(final Nickname firstNickname) {
        return memberRepository.findByNickName(firstNickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}

