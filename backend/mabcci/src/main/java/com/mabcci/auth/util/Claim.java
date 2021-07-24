package com.mabcci.auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Claim {

    TOKEN_TYPE(ClaimType.HEADER, "typ", "JWT"),
    HASH_ALGORITHM(ClaimType.HEADER, "alg", "HS256"),
    ISSUER(ClaimType.PAYLOAD, "iss", "mabcci system"),
    SUBJECT(ClaimType.PAYLOAD, "sub", "authorize member"),
    AUDIENCE(ClaimType.PAYLOAD, "aud", "member");

    private final ClaimType type;
    private final String key;
    private final String value;
}
