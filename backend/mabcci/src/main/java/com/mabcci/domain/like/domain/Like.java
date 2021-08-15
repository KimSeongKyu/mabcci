package com.mabcci.domain.like.domain;

import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.member.domain.Member;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Like extends BaseTimeEntity {

    @Column(name = "like_status")
    @ColumnDefault("1")
    private Boolean status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_member_id", nullable = false)
    private Member member;

    protected Like() {
    }

    public Like(final Member member) {
        this.member = member;
    }

    public Boolean status() {
        return status;
    }

    public Member member() {
        return member;
    }
}
