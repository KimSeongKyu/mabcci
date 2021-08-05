package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.response.MemberListResponse;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.global.common.Nickname;
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
    public Member findByNickName(final Nickname nickname) {
        return memberRepository.findByNicknameWithMemberSpecs(nickname).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<MemberListResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());
    }
}
