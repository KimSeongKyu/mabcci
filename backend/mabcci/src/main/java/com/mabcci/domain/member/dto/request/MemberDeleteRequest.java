package com.mabcci.domain.member.dto.request;

import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;

import javax.validation.Valid;

public final class MemberDeleteRequest {
    @Valid
    private Nickname nickname;

    @Valid
    private Password password;

    private MemberDeleteRequest() {
    }

    public MemberDeleteRequest(@Valid final Nickname nickname, @Valid final Password password) {
        this.nickname = nickname;
        this.password = password;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final Password getPassword() {
        return password;
    }
}
