package com.mabcci.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Password {

    @NotEmpty
    @Column(name = "password")
    private String password;

    public static Password of(final String password) {
        return new Password(password);
    }

    protected Password() {}

    private Password(final String password) {
        this.password = password;
    }
}
