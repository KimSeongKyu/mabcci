package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.model.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberFindService {

    private final MemberRepository memberRepository;

    public MemberFindService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByNickName(final Nickname nickname) {
        final Member findingEntity = memberRepository.findByNickname(nickname)
                .orElseThrow(MemberNotFoundException::new);
        return new MemberResponseDto(findingEntity);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }
}
