package com.mabcci.auth.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Claim {

    TOKEN_TYPE("typ", "JWT"),
    HASH_ALGORITHM("alg", "HS256");

    private final String key;
    private final String value;
}
