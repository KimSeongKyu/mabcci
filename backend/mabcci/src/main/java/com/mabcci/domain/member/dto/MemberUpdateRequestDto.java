package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;

public class MemberUpdateRequestDto {

    private String nickname;
    private Gender gender;

    private MemberUpdateRequestDto() {
    }

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
