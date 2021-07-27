package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Password;
import com.mabcci.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Member extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    @Id
    private Long id;

    @Embedded
    private Email email;

    @Column(name = "MEMBER_PASSWORD", nullable = false)
    private Password password;

    @Column(name = "MEMBER_NICKNAME", nullable = false, unique = true)
    private String nickname;

    @Column(name = "MEMBER_PHONE", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_GENDER", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE", nullable = false)
    private MemberRole role;

    public static final MemberBuilder builder() {
        return new MemberBuilder();
    }

    protected Member() { }

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
        return this.password.equals(otherPassword);
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

        private MemberBuilder() { }

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
