package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@DynamicInsert
public class Ootd extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ootd_member_id", nullable = false)
    private Member member;

    @NotNull
    @Column(name = "ootd_content", nullable = false)
    private String content;

    @Column(name = "ootd_top")
    private String top;

    @Column(name = "ootd_bottom")
    private String bottom;

    @Column(name = "ootd_shoes")
    private String shoes;

    @Column(name = "ootd_accessory")
    private String accessory;

    @NotNull
    @PositiveOrZero
    @Column(name = "ootd_views", nullable = false)
    private Long views;

    protected Ootd() {
    }

    protected Ootd(final OotdBuilder ootdBuilder) {
        this.member = ootdBuilder.member;
        this.content = ootdBuilder.content;
        this.top = ootdBuilder.top;
        this.bottom = ootdBuilder.bottom;
        this.shoes = ootdBuilder.shoes;
        this.accessory = ootdBuilder.accessory;
    }

    public static OotdBuilder builder() {
        return new OotdBuilder();
    }

    public static class OotdBuilder {
        private Member member;
        private String content;
        private String top;
        private String bottom;
        private String shoes;
        private String accessory;

        private OotdBuilder() {
        }

        public OotdBuilder member(final Member member) {
            this.member = member;
            return this;
        }

        public OotdBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public OotdBuilder top(final String top) {
            this.top = top;
            return this;
        }

        public OotdBuilder bottom(final String bottom) {
            this.bottom = bottom;
            return this;
        }

        public OotdBuilder shoes(final String shoes) {
            this.shoes = shoes;
            return this;
        }

        public OotdBuilder accessory(final String accessory) {
            this.accessory = accessory;
            return this;
        }

        public Ootd build() {
            return new Ootd(this);
        }
    }

}
