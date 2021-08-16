package com.mabcci.domain.follow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Nickname;

public class FollowResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

    FollowResponse() {
    }

    public static final FollowResponse ofFollower(final Follow follow) {
        final Member follower = follow.follower();
        return ofMember(follower);
    }

    public static final FollowResponse ofFollowing(final Follow follow) {
        final Member following = follow.following();
        return ofMember(following);
    }

    private static final FollowResponse ofMember(final Member member) {
        final Nickname nickname = member.nickname();
        final String picture = member.picture();
        return new FollowResponse(nickname.nickname(), picture);
    }

    FollowResponse(final String name, final String picture) {
        this.name = name;
        this.picture = picture;
    }

}
