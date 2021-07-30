package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class MemberResponseDto {

    @Valid
    @NotNull
    @NotBlank
    private Long id;

    @Valid
    private Email email;

    @Valid
    private Nickname nickname;

    @Valid
    private Gender gender;

    @Valid
    private MemberRole role;

    private MemberResponseDto() {
    }

    public MemberResponseDto(final Member entity) {
        this(entity.id(), entity.email(), entity.nickname(), entity.gender(), entity.role());
    }

    public MemberResponseDto(final Long id, @Valid final Email email, @Valid final Nickname nickname, @Valid final Gender gender, @Valid final MemberRole role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
    }

    public final Long getId() {
        return id;
    }

    public final Email getEmail() {
        return email;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final Gender getGender() {
        return gender;
    }

    public final MemberRole getRole() {
        return role;
    }

}
