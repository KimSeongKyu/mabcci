package com.mabcci.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Nickname {

    @NotEmpty
    @Column(name = "nickname", nullable = false)
    private String nickname;

    public static Nickname of(final String nickname) {
        return new Nickname(nickname);
    }

    protected Nickname() { }

    private Nickname(final String nickname) {
        this.nickname = nickname;
    }

    public String nickname() {
        return nickname;
    }
}
