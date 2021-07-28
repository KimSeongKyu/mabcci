package com.mabcci.domain.member.dto;

import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;

import javax.validation.Valid;

public final class MemberDeleteRequestDto {
    @Valid
    private Nickname nickname;

    @Valid
    private Password password;

    private MemberDeleteRequestDto() {
    }

    public MemberDeleteRequestDto(@Valid final Nickname nickname, @Valid final Password password) {
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