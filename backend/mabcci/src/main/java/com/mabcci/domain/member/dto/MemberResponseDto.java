package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberResponseDto {

    @NotNull
    @NotBlank
    private final Long id;

    @NotNull
    @NotBlank
    private final String nickname;

    @NotNull
    @NotBlank
    private final MemberRole role;

    public MemberResponseDto(Member entity) {
        this(entity.id(), entity.nickname(), entity.role());
    }

    public MemberResponseDto(Long id, String nickname, MemberRole role) {
        this.id = id;
        this.nickname = nickname;
        this.role = role;
    }

}
