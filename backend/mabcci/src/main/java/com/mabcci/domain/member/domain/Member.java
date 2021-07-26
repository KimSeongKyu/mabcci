package com.mabcci.domain.member.domain;

import com.mabcci.global.common.BaseTimeEntity;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Member extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    @Id
    private Long id;

    @Column(name = "MEMBER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "MEMBER_PASSWORD", nullable = false)
    private String password;

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

    protected Member() {
    }

    @Builder
    public Member(String email, String password, String nickname, String phone, Gender gender, MemberRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
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
        return email;
    }

    public Member update(String nickName, Gender gender) {
        this.nickname = nickName;
        this.gender = gender;
        return this;
    }

    public Gender gender() {
        return gender;
    }
}
