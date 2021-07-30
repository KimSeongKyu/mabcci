package com.mabcci.domain.auth.domain;

import com.mabcci.domain.model.Email;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @EmbeddedId
    @AttributeOverride(name = "email", column =
    @Column(name = "refresh_token_email", nullable = false, unique = true))
    private Email email;

    @NotBlank
    @Column(name = "refresh_token", length = 500, nullable = false, unique = true)
    private String refreshToken;

    @Builder
    public RefreshToken(final Email email, final String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
