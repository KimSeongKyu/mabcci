package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @DisplayName("Email 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final String value = "kwj1270@naver.com";
        final Email email = Email.of(value);

        assertAll(
                () -> assertThat(email).isNotNull(),
                () -> assertThat(email).isExactlyInstanceOf(Email.class)
        );
    }

    @DisplayName("Email 인스턴스 email() 기능 테스트")
    @Test
    void email_test() {
        final String value = "kwj1270@naver.com";
        final Email email = Email.of(value);

        assertThat(email.email()).isEqualTo(value);
    }

    @DisplayName("Email 인스턴스 equals() & hashCode() 기능 테스트")
    @Test
    void equals_and_hashcode_test() {
        final String value = "kwj1270@naver.com";
        final Email email = Email.of(value);
        final Email otherEmail = Email.of(value);

        assertAll(
                () -> assertThat(email).isEqualTo(otherEmail),
                () -> assertThat(email.hashCode()).isEqualTo(otherEmail.hashCode())
        );
    }

}