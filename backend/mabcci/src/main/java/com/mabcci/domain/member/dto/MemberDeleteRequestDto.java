package com.mabcci.domain.member.dto;

public class MemberDeleteRequestDto {
    private String nickname;
    private String password;

    private MemberDeleteRequestDto() {
    }

    public MemberDeleteRequestDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
