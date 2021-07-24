package com.mabcci.auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {

    ACCESS_TOKEN(1000 * 60 * 30),
    REFRESH_TOKEN(1000 * 60 * 60 * 24 * 7);

    private final long expirationTime;
}
