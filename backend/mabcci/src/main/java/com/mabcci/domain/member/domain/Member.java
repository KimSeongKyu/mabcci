package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import com.mabcci.domain.model.Phone;
import com.mabcci.global.common.BaseTimeEntity;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole role;

    public static MemberBuilder builder() {
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

    public boolean checkPassword(final Password otherPassword) {
        return password.checkPassword(otherPassword);
    }

    public Long id() {
        return id;
    }

    public Nickname nickname() {
        return nickname;
    }

    public MemberRole role() {
        return role;
    }

    public Email email() {
        return email;
    }

    public Member update(final Nickname nickName, final Gender gender) {
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
        private Nickname nickname;
        private Phone phone;
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

        public MemberBuilder nickname(final String nickname) {
            return nickname(Nickname.of(nickname));
        }

        public MemberBuilder nickname(final Nickname nickname) {
            this.nickname = nickname;
            return this;
        }

        public MemberBuilder phone(final String phone) {
            return phone(Phone.of(phone));
        }

        public MemberBuilder phone(final Phone phone) {
            this.phone = phone;
            return this;
        }

        public MemberBuilder gender(final Gender gender) {
            this.gender = gender;
            return this;
        }

        public MemberBuilder role(final MemberRole role) {
            this.role = role;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

}