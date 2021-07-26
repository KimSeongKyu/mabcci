package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberResponseDto {

    @NotNull
    @NotBlank
    private final Long id;

    @Email
    private final String email;

    @NotNull
    @NotBlank
    private final String nickname;

    @NotNull
    @NotBlank
    private final Gender gender;

    @NotNull
    @NotBlank
    private final MemberRole role;

    public MemberResponseDto(Member entity) {
        this(entity.id(), entity.email(), entity.nickname(), entity.gender(), entity.role());
    }

    public MemberResponseDto(Long id, String email, String nickname, Gender gender, MemberRole role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public MemberRole getRole() {
        return role;
    }

}
