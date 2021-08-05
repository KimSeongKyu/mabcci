package com.mabcci.domain.follow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;

public class FollowSaveRequest {

    @Valid
    @JsonProperty("following")
    private Nickname following;

    @Valid
    @JsonProperty("follower")
    private Nickname follower;

    FollowSaveRequest() {
    }

    public FollowSaveRequest(@Valid final Nickname following, @Valid final Nickname follower) {
        this.following = following;
        this.follower = follower;
    }

    public Nickname following() {
        return following;
    }

    public Nickname follower() {
        return follower;
    }
}