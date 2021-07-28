package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.model.Nickname;

import javax.validation.Valid;

public final class MemberUpdateRequestDto {

    @Valid
    private Nickname nickname;

    @Valid
    private Gender gender;

    private MemberUpdateRequestDto() {
    }

    public MemberUpdateRequestDto(@Valid final Nickname nickname, @Valid final Gender gender) {
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
