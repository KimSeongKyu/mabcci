package com.mabcci.domain.follow.dto;

import com.mabcci.global.common.Nickname;

import javax.validation.Valid;

public class SaveResponse {

    @Valid
    private Nickname following;

    @Valid
    private Nickname follower;

    SaveResponse() {
    }

    public SaveResponse(@Valid final Nickname following, @Valid final Nickname follower) {
        this.following = following;
        this.follower = follower;
    }

}
