package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberSpecs;
import com.mabcci.domain.member.dto.MemberResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberJoiningService {

    private final MemberRepository memberRepository;

    public MemberJoiningService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto join(final Member member) {
        final MemberSpecs memberSpecs = MemberSpecs.Build().build();
        member.updateMemberSpecs(memberSpecs);
        return new MemberResponseDto(memberRepository.save(member));
    }

}
