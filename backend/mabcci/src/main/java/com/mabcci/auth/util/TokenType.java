package com.mabcci.auth.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenType {

    ACCESS_TOKEN(1000 * 60 * 30),
    REFRESH_TOKEN(1000 * 60 * 60 * 24 * 30);

    private final long expirationTime;
}
