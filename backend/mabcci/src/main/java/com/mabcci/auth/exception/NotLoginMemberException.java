package com.mabcci.auth.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotLoginMemberException extends RuntimeException{

    private final String email;
}
