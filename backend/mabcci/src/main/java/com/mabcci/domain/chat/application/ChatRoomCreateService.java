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

    public String createChatting(final Nickname proposal, final Nickname mabcci) {
        final ChatRoom chatRoom = new ChatRoom();
        final Member pro = findMemberByNickName(proposal);
        final Member mab = findMemberByNickName(mabcci);
        chattingRepository.save(new Chatting(pro, chatRoom));
        chattingRepository.save(new Chatting(mab, chatRoom));
        return chatRoom.getId();
    }

    private Member findMemberByNickName(final Nickname firstNickname) {
        return memberRepository.findByNickName(firstNickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}

