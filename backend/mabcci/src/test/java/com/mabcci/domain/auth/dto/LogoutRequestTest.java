package com.mabcci.domain.auth.dto;

import com.mabcci.domain.model.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

public class LogoutRequestTest {

    @DisplayName("LogoutRequest 인스턴스 생성 테스트")
    @Test
    public void constructTest() {
        final LogoutRequest logoutRequest = new LogoutRequest(EMAIL);
        assertThat(logoutRequest).isNotNull();
    }

    @DisplayName("LogoutRequest email() 테스트")
    @Test
    public void getEmailTest() {
        final LogoutRequest logoutRequest = new LogoutRequest(EMAIL);
        final Email email = logoutRequest.getEmail();
        assertThat(email).isEqualTo(EMAIL);
    }
}
