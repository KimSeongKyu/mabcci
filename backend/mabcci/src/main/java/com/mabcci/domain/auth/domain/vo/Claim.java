package com.mabcci.domain.auth.domain.vo;

public enum Claim {

    TOKEN_TYPE(ClaimType.HEADER, "typ", "JWT"),
    HASH_ALGORITHM(ClaimType.HEADER, "alg", "HS256"),
    ISSUER(ClaimType.PAYLOAD, "iss", "mabcci system"),
    SUBJECT(ClaimType.PAYLOAD, "sub", "authorize member"),
    AUDIENCE(ClaimType.PAYLOAD, "aud", "member");

    public final static String EXPIRATION_KEY = "exp";
    public final static String NOT_BEFORE_KEY = "nbf";
    public final static String ISSUED_AT_KEY = "iat";
    public final static String EMAIL_KEY = "email";
    public final static String NICKNAME_KEY = "nickname";
    public final static String ROLE_KEY = "role";

    private final ClaimType type;
    private final String key;
    private final String value;

    Claim(final ClaimType type, final String key, final String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public final ClaimType type() {
        return type;
    }

    public final String key() {
        return key;
    }

    public final String value() {
        return value;
    }
}
