package com.mabcci.domain.auth.application;

import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.TokenType;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.dto.LogoutRequest;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.model.Email;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
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

    @DisplayName("AuthService 로그인 성공 테스트")
    @Test
    public void loginSuccessTest() {
        final String accessToken = "test.access.token";
        final String refreshToken = "test.refresh.token";

        doReturn(Optional.of(member)).when(memberRepository).findByEmailAndPassword(EMAIL, PASSWORD);
        doReturn(accessToken).when(jwtUtil).createToken(TokenType.ACCESS_TOKEN, EMAIL);
        doReturn(refreshToken).when(jwtUtil).createToken(TokenType.REFRESH_TOKEN, EMAIL);
        doReturn(RefreshToken.builder()
                .email(EMAIL)
                .refreshToken(refreshToken)
                .build()).when(refreshTokenRepository).save(any());

        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);
        final LoginResponse loginResponse = authService.login(loginRequest);

        verify(memberRepository, times(1)).findByEmailAndPassword(EMAIL, PASSWORD);
        verify(jwtUtil, times(2)).createToken(any(), any());
        verify(refreshTokenRepository, times(1)).save(any());

        assertAll(
                () -> assertThat(loginResponse.getAccessToken()).isEqualTo(accessToken),
                () -> assertThat(loginResponse.getRefreshToken()).isEqualTo(refreshToken)
        );
    }

    @DisplayName("AuthService 로그인 실패 테스트")
    @Test
    public void loginFailTest() {
        final LoginRequest loginRequest = new LoginRequest(Email.of(Strings.EMPTY), PASSWORD);
        assertThatExceptionOfType(MemberNotFoundException.class).isThrownBy(() -> authService.login(loginRequest));
    }

    @DisplayName("AuthService 로그아웃 성공 테스트")
    @Test
    public void logoutSuccessTest() {
        final RefreshToken refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken("test.refresh.token")
                .build();
        doReturn(Optional.of(refreshToken)).when(refreshTokenRepository).findById(any());
        doNothing().when(refreshTokenRepository).delete(any());

        authService.logout(new LogoutRequest(EMAIL));

        verify(refreshTokenRepository, times(1)).findById(any());
        verify(refreshTokenRepository, times(1)).delete(any());
    }

    @DisplayName("AuthService 로그아웃 실패 테스트")
    @Test
    public void logoutFailTest() {
        assertThatExceptionOfType(NotLoginMemberException.class).isThrownBy(() -> authService.logout(new LogoutRequest(EMAIL)));
    }
}
