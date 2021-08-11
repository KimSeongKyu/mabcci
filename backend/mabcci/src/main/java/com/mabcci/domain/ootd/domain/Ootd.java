package com.mabcci.domain.ootd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdcategory.domain.OotdCategory;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.picture.domain.Picture;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
public class Ootd extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_id")
    private Long id;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
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

    @PositiveOrZero
    @Column(name = "ootd_views", nullable = false)
    @ColumnDefault("0")
    private Long views;

    @JsonIgnore
    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL)
    private Set<OotdCategory> ootdCategories = new HashSet<>();

    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OotdLike> ootdLikes = new ArrayList<>();

    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OotdPicture> ootdPictures = new ArrayList<>();

    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OotdHashtag> ootdHashtags = new ArrayList<>();

    protected Ootd() {
    }

    protected Ootd(final OotdBuilder ootdBuilder) {
        this.member = ootdBuilder.member;
        this.content = ootdBuilder.content;
        this.top = ootdBuilder.top;
        this.bottom = ootdBuilder.bottom;
        this.shoes = ootdBuilder.shoes;
        this.accessory = ootdBuilder.accessory;
        this.views = ootdBuilder.views;
    }

    public static OotdBuilder builder() {
        return new OotdBuilder();
    }

    public Long id() {
        return id;
    }

    public Member member() {
        return member;
    }

    public String content() {
        return content;
    }

    public String top() {
        return top;
    }

    public String bottom() {
        return bottom;
    }

    public String shoes() {
        return shoes;
    }

    public String accessory() {
        return accessory;
    }

    public Long views() {
        return views;
    }

    public List<OotdLike> ootdLikes() {
        return ootdLikes;
    }

    public List<OotdPicture> ootdPictures() {
        return ootdPictures;
    }

    public List<OotdHashtag> ootdHashtags() {
        return ootdHashtags;
    }

    public Ootd update(final OotdUpdateRequest ootdUpdateRequest) {
        this.content = ootdUpdateRequest.getContent();
        this.top = ootdUpdateRequest.getTop();
        this.bottom = ootdUpdateRequest.getBottom();
        this.shoes = ootdUpdateRequest.getShoes();
        this.accessory = ootdUpdateRequest.getAccessory();

        return this;
    }

    public static class OotdBuilder {
        private Member member;
        private String content;
        private String top;
        private String bottom;
        private String shoes;
        private String accessory;
        private Long views;

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

        public OotdBuilder views(final Long views) {
            this.views = views;
            return this;
        }

        public Ootd build() {
            return new Ootd(this);
        }
    }

}
