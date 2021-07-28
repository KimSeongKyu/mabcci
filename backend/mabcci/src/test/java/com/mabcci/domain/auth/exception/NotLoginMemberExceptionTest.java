package com.mabcci.domain.auth.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

public class NotLoginMemberExceptionTest {

    @DisplayName("NotLoginMemberException 생성 테스트")
    @Test
    public void constructTest() {
        final NotLoginMemberException notLoginMemberException = new NotLoginMemberException(EMAIL);
        assertThat(notLoginMemberException).isNotNull();
    }

    @DisplayName("NotLoginMemberException toString 테스트")
    @Test
    public void toStringTest() {
        final NotLoginMemberException notLoginMemberException = new NotLoginMemberException(EMAIL);
        final String expectedToString = String.format("%s : 로그인된 계정이 아닙니다.", EMAIL.email());
        assertThat(notLoginMemberException.toString()).isEqualTo(expectedToString);
    }
}
