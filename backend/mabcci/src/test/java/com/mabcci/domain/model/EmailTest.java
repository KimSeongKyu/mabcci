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

}