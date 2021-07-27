package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Password;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class JoinRequest {

    @Valid
    private Email email;

    @Valid
    private Password password;

    @NotNull
    @NotEmpty
    private String nickname;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private Gender gender;

    private JoinRequest() {
    }

    public JoinRequest(final String email, final String password, final String nickname, final String phone, final Gender gender) {
        this(Email.of(email), Password.of(password), nickname, phone, gender);
    }

    public JoinRequest(@Valid Email email, @Valid Password password, final String nickname, final String phone, final Gender gender) {
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
