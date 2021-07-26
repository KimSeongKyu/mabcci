package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

public class MemberSaveDto {

    private final String email;
    private final String password;
    private final String nickname;
    private final String phone;
    private final Gender gender;

    public MemberSaveDto(String email, String password, String nickname, String phone, Gender gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public Gender getGender() {
        return gender;
    }
}
