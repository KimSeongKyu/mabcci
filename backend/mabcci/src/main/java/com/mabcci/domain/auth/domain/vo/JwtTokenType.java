package com.mabcci.domain.auth.domain.vo;

public enum JwtTokenType {

    ACCESS_TOKEN(1000 * 60 * 30L),
    REFRESH_TOKEN(1000 * 60 * 60 * 24 * 7L);

    private final Long expirationTime;

    JwtTokenType(final Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public final Long expirationTime() {
        return expirationTime;
    }
}
