package com.mabcci.auth.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String email;

    private String refreshToken;

    @Builder
    public RefreshToken(final String email, final String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
