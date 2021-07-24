package com.mabcci.auth.domain;

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

    public final static String EXPIRATION = "exp";
    public final static String NOT_BEFORE = "nbf";
    public final static String ISSUED_AT = "iat";
    public final static String NICK_NAME = "nickName";

    private final ClaimType type;
    private final String key;
    private final String value;
}
