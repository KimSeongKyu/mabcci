package com.mabcci.domain.auth.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mabcci.domain.model.Email;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class RefreshToken {

    @EmbeddedId
    @AttributeOverride(name = "email", column =
    @Column(name = "refresh_token_email", nullable = false, unique = true))
    private Email email;

    @NotBlank
    @Column(name = "refresh_token", length = 500, nullable = false, unique = true)
    private String refreshToken;

    protected RefreshToken() {
    }

    protected RefreshToken(final RefreshTokenBuilder refreshTokenBuilder) {
        this.email = refreshTokenBuilder.email;
        this.refreshToken = refreshTokenBuilder.refreshToken;
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    public Email email() {
        return email;
    }

    @JsonValue
    public String refreshToken() {
        return refreshToken;
    }

    public static class RefreshTokenBuilder {
        private Email email;
        private String refreshToken;

        private RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder email(final String email) {
            return email(Email.of(email));
        }

        public RefreshTokenBuilder email(final Email email) {
            this.email = email;
            return this;
        }

        public RefreshTokenBuilder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this);
        }
    }
}
