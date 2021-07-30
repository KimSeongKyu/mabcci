package com.mabcci.domain.auth.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.auth.application.AuthService;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.dto.LogoutRequest;
import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Password;
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

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    public static Stream<Arguments> provideEmailAndPasswordsForValidateLoginRequestTest() {
        return Stream.of(
                Arguments.of(EMAIL, null),
                Arguments.of(EMAIL, ""),
                Arguments.of(null, PASSWORD),
                Arguments.of("", PASSWORD),
                Arguments.of("notEmailFormat", PASSWORD)

        );
    }

    @DisplayName(value = "로그인 API 테스트")
    @Test
    public void loginTest() throws Exception {
        final String accessToken = "test.access.token";
        final String refreshToken = "test.refresh.token";
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);
        final LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

        doReturn(loginResponse).when(authService).login(any());

        mockMvc.perform(post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(accessToken))
                .andExpect(jsonPath("$.refreshToken").value(refreshToken));
    }

    @DisplayName(value = "LoginRequest 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. email: {0} | password: {1}")
    @MethodSource(value = "provideEmailAndPasswordsForValidateLoginRequestTest")
    public void validateLoginRequestTest(Email email, Password password) throws Exception {
        final LoginRequest loginRequest = new LoginRequest(email, password);

        mockMvc.perform(post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName(value = "로그아웃 API 테스트")
    @Test
    public void logoutTest() throws Exception {
        final String logoutRequestString = objectMapper.writeValueAsString(new LogoutRequest(EMAIL));
        doNothing().when(authService).logout(any());

        mockMvc.perform(post("/auth/logout")
                .content(logoutRequestString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName(value = "LogoutRequestDto 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. email: {0}")
    @ValueSource(strings = {"notEmailFormat"})
    @NullAndEmptySource
    public void validateLogoutRequestDto(String email) throws Exception {
        final String logoutRequestDtoString = objectMapper.writeValueAsString(new LogoutRequest(Email.of(email)));

        mockMvc.perform(post("/auth/logout")
                .content(logoutRequestDtoString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
