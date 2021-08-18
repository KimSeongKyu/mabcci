package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UnFollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public UnFollowService(final FollowRepository followRepository,
                           final MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void unfollow(final Nickname followingNickname, final Nickname followerNickname) {
        final Member following = findMemberByNickName(followingNickname);
        final Member follower = findMemberByNickName(followerNickname);
        final Follow follow = findFollow(following, follower);
        followRepository.delete(follow);
    }

    private Follow findFollow(final Member following, final Member follower) {
        return followRepository.findByFollowingAndFollower(following, follower)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Member findMemberByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}
