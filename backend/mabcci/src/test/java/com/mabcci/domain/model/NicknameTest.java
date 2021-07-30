package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NicknameTest {

    public static final Nickname NICKNAME = Nickname.of("nickname");

    @DisplayName("Nickname 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final String value = "nickname";
        final Nickname nickname = Nickname.of(value);

        assertAll(
                () -> assertThat(nickname).isNotNull(),
                () -> assertThat(nickname).isExactlyInstanceOf(Nickname.class)
        );
    }

    @DisplayName("Nickname 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final Nickname nickname = new Nickname();

        assertAll(
                () -> assertThat(nickname).isNotNull(),
                () -> assertThat(nickname).isExactlyInstanceOf(Nickname.class)
        );
    }

    @DisplayName("Nickname 인스턴스 nickname() 기능 테스트")
    @Test
    void nickname_test() {
        final String value = "nickname";
        final Nickname nickname = Nickname.of(value);

        assertThat(nickname.nickname()).isEqualTo(value);
    }

    @DisplayName("Nickname 인스턴스 equals() & hashCode() 기능 테스트")
    @Test
    void equals_and_hashcode_test() {
        final String value = "nickname";
        final Nickname nickname = Nickname.of(value);
        final Nickname other = Nickname.of(value);

        assertAll(
                () -> assertThat(nickname).isEqualTo(other),
                () -> assertThat(nickname.hashCode()).isEqualTo(other.hashCode())
        );
    }

}