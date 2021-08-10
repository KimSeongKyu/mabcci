package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        final Follow follow = generateFollow(following, follower);
        return followRepository.save(follow).id();
    }

    private Follow generateFollow(Member following, Member follower) {
        return Follow.Builder()
                .following(following)
                .follower(follower)
                .build();
    }

    @Transactional
    public void unfollow(final Long followId) {
        followRepository.delete(findFollowById(followId));
    }

    private Member findMemberByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Follow findFollowById(Long followId) {
        return followRepository.findById(followId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
