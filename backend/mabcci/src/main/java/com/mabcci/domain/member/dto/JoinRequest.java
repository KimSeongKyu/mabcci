package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import com.mabcci.domain.model.Phone;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class JoinRequest {

    @Valid
    private Email email;

    @Valid
    private Password password;

    @Valid
    private Nickname nickname;

    @Valid
    private Phone phone;

//    @NotNull
//    @NotEmpty
    private Gender gender;

    private JoinRequest() {
    }

    public JoinRequest(@Valid Email email, @Valid Password password, @Valid Nickname nickname, @Valid Phone phone, final Gender gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Phone getPhone() {
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
