package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.model.Nickname;

import javax.validation.Valid;

public class MemberUpdateRequestDto {

    @Valid
    private Nickname nickname;
    private Gender gender;

    private MemberUpdateRequestDto() {
    }

    public MemberUpdateRequestDto(String nickname, Gender gender) {
        this(Nickname.of(nickname), gender);
    }

    public MemberUpdateRequestDto(@Valid Nickname nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
    }
}
