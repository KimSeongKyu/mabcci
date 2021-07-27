package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberResponseDto {

    @NotNull
    @NotBlank
    private final Long id;

    @Valid
    private final Email email;

    @Valid
    private final Nickname nickname;

    @NotNull
    @NotBlank
    private final Gender gender;

    @NotNull
    @NotBlank
    private final MemberRole role;

    public MemberResponseDto(Member entity) {
        this(entity.id(), entity.email(), entity.nickname(), entity.gender(), entity.role());
    }

    public MemberResponseDto(Long id, Email email, Nickname nickname, Gender gender, MemberRole role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public MemberRole getRole() {
        return role;
    }

}
