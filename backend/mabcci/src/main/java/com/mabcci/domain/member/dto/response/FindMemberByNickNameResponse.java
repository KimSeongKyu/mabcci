package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.*;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;

import javax.validation.constraints.NotBlank;

public final class FindMemberByNickNameResponse {

    @JsonProperty("email")
    private Email email;

    @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("role")
    private MemberRole role;

    @JsonProperty("height")
    private int height;

    @JsonProperty("weight")
    private int weight;

    @JsonProperty("footSize")
    private int footSize;

    @JsonProperty("bodyType")
    private BodyType bodyType;

    FindMemberByNickNameResponse() {
    }

    public static final FindMemberByNickNameResponse ofMember(final Member entity) {
        final MemberSpecs memberSpecs = entity.memberSpecs();
        return fromMemberAndMemberSpecs(entity, memberSpecs);
    }

    private static final FindMemberByNickNameResponse fromMemberAndMemberSpecs(final Member member, final MemberSpecs memberSpecs) {
        final Email email = member.email();
        final Nickname nickname = member.nickname();
        final Gender gender = member.gender();
        final MemberRole memberRole = member.memberRole();
        final int height = memberSpecs.height();
        final int weight = memberSpecs.weight();
        final int footSize = memberSpecs.footSize();
        final BodyType bodyType = memberSpecs.bodyType();
        return new FindMemberByNickNameResponse(email, nickname, gender, memberRole, height, weight, footSize, bodyType);
    }

    private FindMemberByNickNameResponse(final Email email, final Nickname nickname, final Gender gender, final MemberRole role,
                                         final int height, final int weight, final int footSize, final BodyType bodyType) {
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
    }

    public final Email email() {
        return email;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final Gender gender() {
        return gender;
    }

    public final MemberRole role() {
        return role;
    }

    public final int height() {
        return height;
    }

    public final int weight() {
        return weight;
    }

    public final int footSize() {
        return footSize;
    }

    public final BodyType bodyType() {
        return bodyType;
    }

}