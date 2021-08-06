package com.mabcci.domain.auth.dto;

import com.mabcci.global.common.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LogoutRequestTest {

    @DisplayName("LogoutRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final LogoutRequest logoutRequest = new LogoutRequest(EMAIL);

        assertAll(
                () -> assertThat(logoutRequest).isNotNull(),
                () -> assertThat(logoutRequest).isExactlyInstanceOf(LogoutRequest.class)
        );
    }

    @DisplayName("LogoutRequest 인스턴스 email 반환 기능 테스트")
    @Test
    void get_email_test() {
        final LogoutRequest logoutRequest = new LogoutRequest(EMAIL);
        final Email email = logoutRequest.getEmail();
        assertThat(email).isEqualTo(EMAIL);
    }
}
