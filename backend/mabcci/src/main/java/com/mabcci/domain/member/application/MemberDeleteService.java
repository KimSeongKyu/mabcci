package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDeleteService {
    private final MemberRepository memberRepository;

    public MemberDeleteService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
