package com.mabcci.domain.auth.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotLoginMemberException extends RuntimeException {

    private final static String NOT_LOGIN_MEMBER_MESSAGE = " : 로그인된 계정이 아닙니다.";

    private final String email;

    @Override
    public String toString() {
        return email + NOT_LOGIN_MEMBER_MESSAGE;
    }
}
