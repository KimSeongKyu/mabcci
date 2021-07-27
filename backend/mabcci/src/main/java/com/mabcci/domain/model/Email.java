package com.mabcci.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Email {

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    public static Email of(final String value) {
        return new Email(value);
    }

    protected Email() {
    }

    private Email(final String email) {
        this.email = email;
    }
}
