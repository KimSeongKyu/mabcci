package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequestDto;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto update(final MemberUpdateRequestDto memberUpdateRequestDto) {
        final Member entity = memberRepository.findByNickname(memberUpdateRequestDto.getNickname())
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(entity.update(memberUpdateRequestDto.getNickname(), memberUpdateRequestDto.getGender()));
    }

    @Transactional
    public void delete(final Nickname nickname, final Password password) {
        final Member member = memberRepository.findByNicknameAndPassword(nickname, password).stream()
                .filter(entity -> entity.checkPassword(password))
                .findFirst()
                .orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }

}
