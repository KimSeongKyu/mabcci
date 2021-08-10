package com.mabcci.domain.follow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

public final class FollowSaveRequest {

    @JsonProperty("following")
    private Nickname following;

    @JsonProperty("follower")
    private Nickname follower;

    FollowSaveRequest() {
    }

    public FollowSaveRequest(final Nickname following, final Nickname follower) {
        this.following = following;
        this.follower = follower;
    }

    public final Nickname following() {
        return following;
    }

    public final Nickname follower() {
        return follower;
    }

}
