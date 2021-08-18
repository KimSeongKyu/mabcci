package com.mabcci.domain.member.exception;

public final class MemberNotFoundException extends RuntimeException {

    private static final String MESSAGE = "잘못된 유저 닉네임입니다.";

    @Override
    public final String getMessage() {
        return MESSAGE;
    }

}
