package com.mabcci.domain.auth.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NotLoginMemberExceptionTest {

    @DisplayName("NotLoginMemberException 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final NotLoginMemberException notLoginMemberException = new NotLoginMemberException(EMAIL);

        assertAll(
                () -> assertThat(notLoginMemberException).isNotNull(),
                () -> assertThat(notLoginMemberException).isExactlyInstanceOf(NotLoginMemberException.class)
        );
    }

    @DisplayName("NotLoginMemberException 인스턴스 toString 기능 테스트")
    @Test
    void to_string_test() {
        final NotLoginMemberException notLoginMemberException = new NotLoginMemberException(EMAIL);
        final String expectedToString = String.format("%s : 로그인된 계정이 아닙니다.", EMAIL.email());
        assertThat(notLoginMemberException.toString()).isEqualTo(expectedToString);
    }
}
