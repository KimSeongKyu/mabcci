package com.mabcci.auth.service;

import com.mabcci.auth.domain.RefreshToken;
import com.mabcci.auth.domain.RefreshTokenRepository;
import com.mabcci.auth.exception.NotLoginMemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @DisplayName(value = "로그아웃 성공 테스트")
    @Test
    public void logoutSuccessTest() {
        // given
        RefreshToken refreshToken = RefreshToken.builder()
                .email("example@example.com")
                .refreshToken("test.refresh.token")
                .build();
        doReturn(Optional.of(refreshToken)).when(refreshTokenRepository).findById(any());
        doNothing().when(refreshTokenRepository).delete(any());

        // when
        authService.logout(any());

        // then
        verify(refreshTokenRepository, times(1)).findById(any());
        verify(refreshTokenRepository, times(1)).delete(any());
    }

    @DisplayName(value = "로그아웃 실패 테스트")
    @Test
    public void logoutFailTest() {
        // given
        String email = "example@example.com";

        // when and then
        assertThatExceptionOfType(NotLoginMemberException.class).isThrownBy(() -> {
            authService.logout(email);
        });
    }
}
