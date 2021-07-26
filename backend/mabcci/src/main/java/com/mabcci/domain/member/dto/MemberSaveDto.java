package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class MemberSaveDto {

    @Email
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final String nickname;

    @NotNull
    private final String phone;

    @NotNull
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

    public Member entity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .gender(gender)
                .role(MemberRole.USER)
                .build();
    }
}
