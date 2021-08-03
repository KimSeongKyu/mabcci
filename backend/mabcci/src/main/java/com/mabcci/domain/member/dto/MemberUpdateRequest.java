package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;

public final class MemberUpdateRequest {

    @Valid
    private Nickname nickname;

    @Valid
    private Gender gender;

    private MemberUpdateRequest() {
    }

    public MemberUpdateRequest(@Valid final Nickname nickname, @Valid final Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final Gender getGender() {
        return gender;
    }
}
