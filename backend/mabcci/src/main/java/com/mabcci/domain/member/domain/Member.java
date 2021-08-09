package com.mabcci.domain.member.domain;

import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import com.mabcci.global.common.Phone;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "email", column =
    @Column(name = "member_email", nullable = false, unique = true, updatable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "password", column =
    @Column(name = "member_password", nullable = false))
    private Password password;

    @Embedded
    @AttributeOverride(name = "nickname", column =
    @Column(name = "member_nickname", nullable = false, unique = true))
    private Nickname nickname;

    @Embedded
    @AttributeOverride(name = "phone", column =
    @Column(name = "member_phone", nullable = false, unique = true))
    private Phone phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private Gender gender;

    @Column(name = "member_description")
    private String description;

    @Column(name = "member_image")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole memberRole;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_specs_id")
    private MemberSpecs memberSpecs;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<MemberCategory> memberCategories = new HashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followings = new HashSet<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followers = new HashSet<>();

    protected Member() {
    }

    protected Member(final MemberBuilder memberBuilder) {
        this.email = memberBuilder.email;
        this.nickname = memberBuilder.nickname;
        this.password = memberBuilder.password;
        this.phone = memberBuilder.phone;
        this.gender = memberBuilder.gender;
        this.description = memberBuilder.description;
        this.picture = memberBuilder.picture;
        this.memberRole = memberBuilder.memberRole;
        this.memberSpecs = memberBuilder.memberSpecs;
    }

    public static MemberBuilder Builder() {
        return new MemberBuilder();
    }

    public boolean checkPassword(final Password otherPassword) {
        return password.checkPassword(otherPassword);
    }

    public Long id() {
        return id;
    }

    public Nickname nickname() {
        return nickname;
    }

    public MemberRole memberRole() {
        return memberRole;
    }

    public Email email() {
        return email;
    }

    public MemberSpecs memberSpecs() {
        return memberSpecs;
    }

    public Gender gender() {
        return gender;
    }

    public String picture() {
        return picture;
    }

    public Set<MemberCategory> memberCategories() {
        return memberCategories;
    }

    public void addMemberCategory(final MemberCategory memberCategory) {
        memberCategory.changeMember(this);
        if(!memberCategories.contains(memberCategory)) {
            memberCategories.add(memberCategory);
        }
    }

    public Member update(final Nickname nickName, final Gender gender, final String description,
                         int height, int weight, int footSize, BodyType bodyType, final String picture) {
        this.nickname = nickName;
        this.gender = gender;
        this.description = description;
        this.picture = picture;
        updateMemberSpecs(memberSpecs.update(height, weight, footSize, bodyType));
        return this;
    }

    public void updateMemberSpecs(final MemberSpecs memberSpecs) {
        this.memberSpecs = memberSpecs;
    }

    public void clearMemberCategory() {
        memberCategories.stream().forEach(memberCategory -> memberCategory.changeMember(null));
        memberCategories.clear();
    }

    public static class MemberBuilder {

        private Email email;
        private Password password;
        private Nickname nickname;
        private Phone phone;
        private Gender gender;
        private String description;
        private String picture;
        private MemberRole memberRole;
        private MemberSpecs memberSpecs;

        private MemberBuilder() {
        }

        public MemberBuilder email(final Email email) {
            this.email = email;
            return this;
        }

        public MemberBuilder password(final Password password) {
            this.password = password;
            return this;
        }

        public MemberBuilder nickname(final Nickname nickname) {
            this.nickname = nickname;
            return this;
        }

        public MemberBuilder phone(final Phone phone) {
            this.phone = phone;
            return this;
        }

        public MemberBuilder gender(final Gender gender) {
            this.gender = gender;
            return this;
        }

        public MemberBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public MemberBuilder picture(final String picture) {
            this.picture = picture;
            return this;
        }

        public MemberBuilder memberRole(final MemberRole memberRole) {
            this.memberRole = memberRole;
            return this;
        }

        public MemberBuilder memberSpecs(final MemberSpecs memberSpecs) {
            this.memberSpecs = memberSpecs;
            return this;
        }

        public Member build() {
            return new Member(this);
        }

    }

}
