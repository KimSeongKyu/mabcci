package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;

public class MemberUpdateRequestDto {

    private final String nickname;
    private final Gender gender;

    public MemberUpdateRequestDto(String nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
    }

}
