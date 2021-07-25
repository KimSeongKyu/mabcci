package com.mabcci.auth.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotLoginMemberExceptionTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given
        String email = "example@example.com";

        // when
        NotLoginMemberException notLoginMemberException = new NotLoginMemberException(email);

        // then
        assertThat(notLoginMemberException).isNotNull();
    }
}
