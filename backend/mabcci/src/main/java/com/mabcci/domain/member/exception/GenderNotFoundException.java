package com.mabcci.domain.member.exception;

public class GenderNotFoundException extends RuntimeException {

    private static final String MESSAGE = "잘못된 성별 정보입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
