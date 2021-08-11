package com.mabcci.domain.ootdhashtag.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OotdHashtag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_hashtag_id")
    private Long id;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ootd_hashtag_ootd_id", nullable = false)
    private Ootd ootd;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ootd_hashtag_hashtag_id", nullable = false)
    private Hashtag hashtag;

    protected OotdHashtag() {
    }

    private OotdHashtag(final OotdHashtagBuilder ootdHashtagBuilder) {
        this.ootd = ootdHashtagBuilder.ootd;
        this.hashtag = ootdHashtagBuilder.hashtag;
    }

    public static OotdHashtagBuilder builder() {
        return new OotdHashtagBuilder();
    }

    @JsonValue
    public Long id() {
        return id;
    }

    @JsonValue
    public Ootd ootd() {
        return ootd;
    }

    @JsonValue
    public Hashtag hashtag() {
        return hashtag;
    }

    public static class OotdHashtagBuilder {
        private Ootd ootd;
        private Hashtag hashtag;

        private OotdHashtagBuilder() {
        }

        public OotdHashtagBuilder ootd(final Ootd ootd) {
            this.ootd = ootd;
            return this;
        }

        public OotdHashtagBuilder hashtag(final Hashtag hashtag) {
            this.hashtag = hashtag;
            return this;
        }

        public OotdHashtag build() {
            return new OotdHashtag(this);
        }

    }
}
