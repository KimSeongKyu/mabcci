package com.mabcci.domain.auth.service;

import com.mabcci.domain.auth.domain.ClaimType;
import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.TokenType;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.auth.util.JwtUtil;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private Member member;

    @Mock
    private JwtUtil jwtUtil;

    @DisplayName(value = "로그인 성공 테스트")
    @Test
    public void loginSuccessTest() {
        // given
        String email = "example@example.com";
        String password = "testPassword";
        String accessToken = "test.access.token";
        String refreshToken = "test.refresh.token";
        LoginRequest loginRequest = new LoginRequest(email, password);

        doReturn(Optional.of(member)).when(memberRepository).findByEmailAndPassword(email ,password);
        doReturn(accessToken).when(jwtUtil).createToken(TokenType.ACCESS_TOKEN, email);
        doReturn(refreshToken).when(jwtUtil).createToken(TokenType.REFRESH_TOKEN, email);
        doReturn(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build()).when(refreshTokenRepository).save(any());

        // when
        LoginResponse loginResponse = authService.login(loginRequest);

        // then
        verify(memberRepository, times(1)).findByEmailAndPassword(email, password);
        verify(jwtUtil, times(2)).createToken(any(), any());
        verify(refreshTokenRepository, times(1)).save(any());

        assertAll(
                () -> assertThat(loginResponse.getAccessToken()).isEqualTo(accessToken),
                () -> assertThat(loginResponse.getRefreshToken()).isEqualTo(refreshToken)
        );
    }

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
