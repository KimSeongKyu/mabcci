package com.mabcci.domain.auth.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.auth.application.AuthService;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.dto.request.LoginRequest;
import com.mabcci.domain.auth.dto.request.LogoutRequest;
import com.mabcci.domain.auth.dto.response.LoginResponse;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private AuthService authService;

    static Stream<Arguments> provide_email_and_passwords_for_validate_login_request_test() {
        return Stream.of(
                Arguments.of(EMAIL, null),
                Arguments.of(EMAIL, ""),
                Arguments.of(null, PASSWORD),
                Arguments.of("", PASSWORD),
                Arguments.of("notEmailFormat", PASSWORD)

        );
    }

    @DisplayName(value = "AuthController 인스턴스 로그인 API 테스트")
    @Test
    void login_test() throws Exception {
        final JwtToken accessToken = JwtToken.of("test.access.token");
        final JwtToken refreshToken = JwtToken.of("test.refresh.token");
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);
        final LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

        doReturn(loginResponse).when(authService).login(any());

        mockMvc.perform(post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(accessToken.jwtToken()))
                .andExpect(jsonPath("$.refreshToken").value(refreshToken.jwtToken()));
    }

    @DisplayName("AuthController 인스턴스 LoginRequest 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. email: {0} | password: {1}")
    @MethodSource("provide_email_and_passwords_for_validate_login_request_test")
    void validate_login_request_test(final Email email, final Password password) throws Exception {
        final LoginRequest loginRequest = new LoginRequest(email, password);

        mockMvc.perform(post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("AuthController 인스턴스 로그아웃 API 테스트")
    @Test
    void logout_test() throws Exception {
        final String logoutRequestString = objectMapper.writeValueAsString(new LogoutRequest(EMAIL));
        doNothing().when(authService).logout(any());

        mockMvc.perform(post("/auth/logout")
                .content(logoutRequestString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName("AuthController 인스턴스 LogoutRequest 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. email: {0}")
    @ValueSource(strings = {"notEmailFormat"})
    @NullAndEmptySource
    void validate_logout_request_test(final String email) throws Exception {
        final String logoutRequestDtoString = objectMapper.writeValueAsString(new LogoutRequest(Email.of(email)));

        mockMvc.perform(post("/auth/logout")
                .content(logoutRequestDtoString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
