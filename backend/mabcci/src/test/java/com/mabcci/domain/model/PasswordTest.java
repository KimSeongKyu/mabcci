package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @DisplayName("Password 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final String value = "password";
        final Password password = Password.of(value);

        assertAll(
                () -> assertThat(password).isNotNull(),
                () -> assertThat(password).isExactlyInstanceOf(Password.class)
        );
    }

    @DisplayName("Password 인스턴스 디폴자 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final Password password = new Password();

        assertAll(
                () -> assertThat(password).isNotNull(),
                () -> assertThat(password).isExactlyInstanceOf(Password.class)
        );
    }

    @DisplayName("Password 인스턴스 checkPassword() 기능 테스트")
    @Test
    void checkPassword_test() {
        final String value = "password";
        final String invalidValue = "passward";
        final Password password = Password.of(value);

        assertAll(
                () -> assertThat(password.checkPassword(value)).isTrue(),
                () -> assertThat(password.checkPassword(invalidValue)).isFalse()
        );
    }

    @DisplayName("Password 인스턴스 password() 기능 테스트")
    @Test
    void password_test() {
        final String value = "password";
        final Password password = Password.of(value);

        assertThat(password.password()).isEqualTo(value);
    }

    @DisplayName("Password 인스턴스 equals() & hashCode() 기능 테스트")
    @Test
    void equals_and_hashcode_test() {
        final String value = "password";
        final Password password = Password.of(value);
        final Password otherPassword = Password.of(value);

        assertAll(
                () -> assertThat(password).isEqualTo(otherPassword),
                () -> assertThat(password.hashCode()).isEqualTo(otherPassword.hashCode())
        );
    }

}