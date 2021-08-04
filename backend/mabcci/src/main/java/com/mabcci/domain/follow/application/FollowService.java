package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public FollowService(final FollowRepository followRepository,
                         final MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    public Long save(final Nickname followingNickname, final Nickname followerNickname) {
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

    private Member findMemberByNickName(@Valid final Nickname nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }
}
