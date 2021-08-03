package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequestDto;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberUpdateService {

    private final MemberRepository memberRepository;

    public MemberUpdateService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto update(final MemberUpdateRequestDto memberUpdateRequestDto) {
        final Member entity = memberRepository.findByNickname(memberUpdateRequestDto.getNickname())
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(entity.update(memberUpdateRequestDto.getNickname(), memberUpdateRequestDto.getGender()));
    }

}
