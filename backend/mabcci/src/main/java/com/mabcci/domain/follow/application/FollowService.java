package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.follow.dto.FollowResponse;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public FollowService(final FollowRepository followRepository,
                         final MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long follow(final Nickname followingNickname, final Nickname followerNickname) {
        final Member following = findMemberByNickName(followingNickname);
        final Member follower = findMemberByNickName(followerNickname);
        final Follow follow = follow(following, follower);
        return followRepository.save(follow).id();
    }

    private Follow follow(final Member following, final Member follower) {
        return Follow.Builder()
                .following(following)
                .follower(follower)
                .build();
    }

    private Member findMemberByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}
