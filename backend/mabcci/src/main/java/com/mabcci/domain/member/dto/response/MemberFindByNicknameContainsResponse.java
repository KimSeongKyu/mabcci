package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class MemberFindByNicknameContainsResponse {

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("picture")
    private String picture;

    public MemberFindByNicknameContainsResponse(final Nickname nickname, final String picture) {
        this.nickname = nickname;
        this.picture = picture;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final String picture() {
        return picture;
    }
}
