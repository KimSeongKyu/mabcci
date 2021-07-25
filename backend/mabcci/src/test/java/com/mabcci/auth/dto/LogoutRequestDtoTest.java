package com.mabcci.auth.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutRequestDtoTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given
        String email = "example@example.com";

        // when
        LogoutRequestDto logoutRequestDto = new LogoutRequestDto(email);

        // then
        assertThat(logoutRequestDto).isNotNull();
    }
}
