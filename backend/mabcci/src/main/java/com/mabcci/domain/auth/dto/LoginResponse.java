package com.mabcci.domain.auth.dto;

public final class LoginResponse {

    private String accessToken;
    private String refreshToken;

    private LoginResponse() {
    }

    public LoginResponse(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public final String getAccessToken() {
        return accessToken;
    }

    public final String getRefreshToken() {
        return refreshToken;
    }
}
