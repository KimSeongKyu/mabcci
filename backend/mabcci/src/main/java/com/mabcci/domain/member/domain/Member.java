package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Password;
import com.mabcci.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "member_nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "member_phone", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole role;

    public static final MemberBuilder builder() {
        return new MemberBuilder();
    }

    protected Member() {
    }

    protected Member(final MemberBuilder memberBuilder) {
        this.id = memberBuilder.id;
        this.email = memberBuilder.email;
        this.nickname = memberBuilder.nickname;
        this.password = memberBuilder.password;
        this.phone = memberBuilder.phone;
        this.gender = memberBuilder.gender;
        this.role = memberBuilder.role;
    }

    public boolean checkPassword(final String otherPassword) {
        return password.checkPassword(otherPassword);
    }

    public Long id() {
        return id;
    }

    public String nickname() {
        return nickname;
    }

    public MemberRole role() {
        return role;
    }

    public String email() {
        return email.email();
    }

    public String password() {
        return password;
    }

    public Member update(String nickName, Gender gender) {
        this.nickname = nickName;
        this.gender = gender;
        return this;
    }

    public Gender gender() {
        return gender;
    }

    public static class MemberBuilder {
        private Long id;
        private Email email;
        private Password password;
        private String nickname;
        private String phone;
        private Gender gender;
        private MemberRole role;

        private MemberBuilder() {
        }

        public MemberBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public MemberBuilder email(final String email) {
            return email(Email.of(email));
        }

        public MemberBuilder email(final Email email) {
            this.email = email;
            return this;
        }

        public MemberBuilder password(final String password) {
            return password(Password.of(password));
        }

        public MemberBuilder password(final Password password) {
            this.password = password;
            return this;
        }

        public MemberBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public MemberBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public MemberBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public MemberBuilder role(MemberRole role) {
            this.role = role;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

}
