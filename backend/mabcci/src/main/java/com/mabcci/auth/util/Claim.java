package com.mabcci.auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Claim {

    TOKEN_TYPE("typ", "JWT"),
    HASH_ALGORITHM("alg", "HS256");

    private final String key;
    private final String value;
}
