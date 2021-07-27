package com.mabcci.domain.auth.exception;

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

    @DisplayName(value = "toString 테스트")
    @Test
    public void toStringTest() {
        // given
        String email = "example@example.com";
        NotLoginMemberException notLoginMemberException = new NotLoginMemberException(email);

        String expectedToString = email + " : 로그인된 계정이 아닙니다.";

        // when
        String toString = notLoginMemberException.toString();

        // then
        assertThat(toString).isEqualTo(expectedToString);
    }
}
