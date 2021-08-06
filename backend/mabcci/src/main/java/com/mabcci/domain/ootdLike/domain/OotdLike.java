package com.mabcci.domain.ootdLike.domain;

import com.mabcci.domain.like.domain.Like;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.domain.Ootd;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DynamicInsert
public class OotdLike extends Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_like_id")
    private Long id;

    @NotNull
    @Column(name = "ootd_like_ootd", nullable = false, updatable = false)
    private Ootd ootd;

    protected OotdLike() {
    }

    private OotdLike(final OotdLikeBuilder ootdLikeBuilder) {
        super(ootdLikeBuilder.member);
        this.ootd = ootdLikeBuilder.ootd;
    }

    public static OotdLikeBuilder builder() {
        return new OotdLikeBuilder();
    }

    public static class OotdLikeBuilder {
        private Member member;
        private Ootd ootd;

        public OotdLikeBuilder member(final Member member) {
            this.member = member;
            return this;
        }

        public OotdLikeBuilder ootd(final Ootd ootd) {
            this.ootd = ootd;
            return this;
        }

        public OotdLike build() {
            return new OotdLike(this);
        }
    }
}
