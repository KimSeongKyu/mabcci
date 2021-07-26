package com.mabcci.domain.member.exception;

public class MemberNotFoundException extends RuntimeException {
    private final String MESSAGE = "잘못된 유저 닉네임입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
