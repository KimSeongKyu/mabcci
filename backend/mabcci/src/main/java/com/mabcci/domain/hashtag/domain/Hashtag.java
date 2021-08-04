package com.mabcci.domain.hashtag.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Hashtag {

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
