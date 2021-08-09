package com.mabcci.domain.auth.exception;

import com.mabcci.global.common.Email;

public class NotLoginMemberException extends RuntimeException {

    private final static String NOT_LOGIN_MEMBER_MESSAGE = "%s : 로그인된 계정이 아닙니다.";

    private final String email;

    public NotLoginMemberException(final Email email) {
        this(email.email());
    }

    public NotLoginMemberException(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(NOT_LOGIN_MEMBER_MESSAGE, email);
    }
}
