package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NicknameTest {

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
}