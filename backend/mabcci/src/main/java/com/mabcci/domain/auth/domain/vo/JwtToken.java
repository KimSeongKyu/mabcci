package com.mabcci.domain.auth.domain.vo;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Embeddable
public class JwtToken implements Serializable {

    @NotBlank
    @Column(name = "jwt_token", length = 500, nullable = false, unique = true)
    private String jwtToken;

    protected JwtToken() {
    }

    private JwtToken(final String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static JwtToken of(final String jwtToken) {
        return new JwtToken(jwtToken);
    }

    @JsonValue
    public String jwtToken() {
        return jwtToken;
    }
}
