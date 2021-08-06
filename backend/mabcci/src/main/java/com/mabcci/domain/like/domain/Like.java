package com.mabcci.domain.like.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.member.domain.Member;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Like extends BaseTimeEntity {

    @Column(name = "status")
    private Boolean status;

    @NotNull
    @Column(name = "member_id", nullable = false)
    private Member member;

    protected Like() {
    }

    public Like(final Member member) {
        this.member = member;
    }

    @JsonValue
    public Boolean status() {
        return status;
    }

    @JsonValue
    public Member member() {
        return member;
    }
}
