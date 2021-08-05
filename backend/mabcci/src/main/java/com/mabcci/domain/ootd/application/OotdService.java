package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;

    public OotdService(final MemberRepository memberRepository, final OotdRepository ootdRepository) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
    }

    @Transactional
    public Ootd saveOotd(final OotdSaveRequest ootdSaveRequest) {
        final Member member = memberRepository.findByNickname(Nickname.of(ootdSaveRequest.getNickname()))
                .orElseThrow(IllegalArgumentException::new);
        final Ootd ootd = Ootd.builder()
                .member(member)
                .content(ootdSaveRequest.getContent())
                .top(ootdSaveRequest.getTop())
                .bottom(ootdSaveRequest.getBottom())
                .shoes(ootdSaveRequest.getShoes())
                .accessory(ootdSaveRequest.getAccessory())
                .build();
        return ootdRepository.save(ootd);
    }
}
