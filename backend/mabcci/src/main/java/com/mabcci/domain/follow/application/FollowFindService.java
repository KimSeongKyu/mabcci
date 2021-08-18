package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.follow.dto.FollowResponse;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowFindService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public FollowFindService(final FollowRepository followRepository,
                             final MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    public List<FollowResponse> myFollower(final Nickname nickname) {
        final Member member = findMemberByNickname(nickname);
        final List<Follow> followings = followRepository.findByFollower(member);
        return followings.stream()
                .map(FollowResponse::ofFollower)
                .collect(Collectors.toList());
    }

    public List<FollowResponse> myFollowing(final Nickname nickname) {
        final Member member = findMemberByNickname(nickname);
        final List<Follow> followings = followRepository.findByFollowing(member);
        return followings.stream()
                .map(FollowResponse::ofFollowing)
                .collect(Collectors.toList());
    }

    private Member findMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

}
