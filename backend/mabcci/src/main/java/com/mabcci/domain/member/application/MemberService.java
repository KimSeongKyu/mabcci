package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequestDto;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto join(Member member) {
        return new MemberResponseDto(memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByNickName(String nickname) {
        Member findingEntity = memberRepository.findByNickname(nickname)
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(findingEntity);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponseDto update(MemberUpdateRequestDto memberUpdateRequestDto) {
        Member entity = memberRepository.findByNickname(memberUpdateRequestDto.getNickname())
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(entity.update(memberUpdateRequestDto.getNickname(), memberUpdateRequestDto.getGender()));
    }

    @Transactional
    public void delete(String nickname, String password) {
        Member member = memberRepository.findByNicknameAndPassword(nickname, password).stream()
                .filter(entity -> entity.checkPassword(password))
                .findFirst()
                .orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }

}
