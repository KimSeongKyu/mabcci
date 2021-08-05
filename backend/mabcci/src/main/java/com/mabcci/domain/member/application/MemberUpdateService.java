package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberUpdateService {

    private final MemberRepository memberRepository;

    public MemberUpdateService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member update(final Nickname nickname, final Gender gender) {
        final Member entity = memberRepository.findByNicknameWithMemberSpecs(nickname)
                .orElseThrow(MemberNotFoundException::new);
        return entity.update(nickname, gender);
    }
}
