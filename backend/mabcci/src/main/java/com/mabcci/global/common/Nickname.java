package com.mabcci.global.common;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Embeddable
public class Nickname {

    @NotEmpty
    @Column(name = "nickname", nullable = false)
    private String nickname;

    public static Nickname of(final String nickname) {
        return new Nickname(nickname);
    }

    protected Nickname() {
    }

    private Nickname(final String nickname) {
        this.nickname = nickname;
    }

    @JsonValue
    public String nickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nickname nickname1 = (Nickname) o;
        return Objects.equals(nickname(), nickname1.nickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname());
    }
}
