package com.mabcci.domain.member.dto;

import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;

public class MemberDeleteRequestDto {
    private Nickname nickname;
    private Password password;

    private MemberDeleteRequestDto() {
    }

    public MemberDeleteRequestDto(Nickname nickname, Password password) {
        this.nickname = nickname;
        this.password = password;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Password getPassword() {
        return password;
    }
}
