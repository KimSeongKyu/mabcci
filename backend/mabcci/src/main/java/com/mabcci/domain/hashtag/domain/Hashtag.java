package com.mabcci.domain.hashtag.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mabcci.domain.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Hashtag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @NotNull
    @Column(name = "hashtag_name", nullable = false, unique = true)
    private String name;

    protected Hashtag() {
    }

    private Hashtag(final HashtagBuilder hashtagBuilder) {
        this.name = hashtagBuilder.name;
    }

    public static HashtagBuilder builder() {
        return new HashtagBuilder();
    }

    @JsonValue
    public Long id() {
        return id;
    }

    @JsonValue
    public String name() {
        return name;
    }

    public static class HashtagBuilder {
        private String name;

        private HashtagBuilder() {
        }

        public HashtagBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public Hashtag build() {
            return new Hashtag(this);
        }
    }
}
