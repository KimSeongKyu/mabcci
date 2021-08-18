package com.mabcci.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;

import javax.validation.Valid;

public final class MemberDeleteRequest {

    @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("password")
    private Password password;

    private MemberDeleteRequest() {
    }

    public MemberDeleteRequest(final Nickname nickname, final Password password) {
        this.nickname = nickname;
        this.password = password;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final Password password() {
        return password;
    }
}
