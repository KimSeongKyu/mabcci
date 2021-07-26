package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByNickName(String nickName) {
        Member findingMember = memberRepository.findByNickname(nickName)
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(findingMember);
    }

}
