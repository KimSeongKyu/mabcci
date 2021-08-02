package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import com.mabcci.domain.model.Phone;

import javax.validation.Valid;

public final class JoinRequest {

    @Valid
    private Email email;

    @Valid
    private Password password;

    @Valid
    private Nickname nickname;

    @Valid
    private Phone phone;

    @Valid
    private Gender gender;

    private JoinRequest() {
    }

    public JoinRequest(@Valid final Email email, @Valid final Password password,
                       @Valid final Nickname nickname, @Valid final Phone phone, @Valid final Gender gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
    }

    public final Email getEmail() {
        return email;
    }

    public final Password getPassword() {
        return password;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final Phone getPhone() {
        return phone;
    }

    public final Gender getGender() {
        return gender;
    }

    public final Member entity() {
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
