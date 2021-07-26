package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;

import javax.validation.constraints.Email;

public class MemberUpdateRequestDto {

    private final String nickName;
    private final Gender gender;

    public MemberUpdateRequestDto(String nickName, Gender gender) {
        this.nickName = nickName;
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public Gender getGender() {
        return gender;
    }

}
