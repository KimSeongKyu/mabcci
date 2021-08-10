package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.member.domain.*;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class MemberListResponse {

    @NotBlank
    private Long id;
    private Email email;
    private Nickname nickname;
    private Gender gender;
    private MemberRole role;

    MemberListResponse() {
    }

    public MemberListResponse(final Member entity) {
        this(entity.id(), entity.email(), entity.nickname(),
                entity.gender(), entity.memberRole());
    }

    private MemberListResponse(final Long id, final Email email, final Nickname nickname,
                               final Gender gender, final MemberRole role) {
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