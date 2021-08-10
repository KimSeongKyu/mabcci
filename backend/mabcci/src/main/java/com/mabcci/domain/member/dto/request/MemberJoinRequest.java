package com.mabcci.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import com.mabcci.global.common.Phone;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

public final class MemberJoinRequest {

    @JsonProperty("email")
    private Email email;

    @JsonProperty("password")
    private Password password;

    @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("phone")
    private Phone phone;

    @JsonProperty("gender")
    private Gender gender;

    @Size(min = 1)
    @JsonProperty("categories")
    private Set<String> categories;

    MemberJoinRequest() {
    }

    public MemberJoinRequest(final Email email, final Password password, final Nickname nickname,
                             final Phone phone, final Gender gender, final Set<String> categories) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
        this.categories = categories;
    }

    public final Email email() {
        return email;
    }

    public final Password password() {
        return password;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final Phone phone() {
        return phone;
    }

    public final Gender gender() {
        return gender;
    }

    public final Set<String> categories() {
        return categories;
    }

    public final Member member() {
        return Member.Builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .gender(gender)
                .memberRole(MemberRole.USER)
                .build();
    }

}
