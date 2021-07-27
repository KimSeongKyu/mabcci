package com.mabcci.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.auth.dto.LogoutRequestDto;
import com.mabcci.domain.auth.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @DisplayName(value = "로그아웃 API 테스트")
    @Test
    public void logoutTest() throws Exception {
        // given
        String api = "/auth/logout";
        String email = "example@example.com";
        String logoutRequestDto = objectMapper.writeValueAsString(
                LogoutRequestDto.builder()
                        .email(email)
                        .build());

        doNothing().when(authService).logout(email);

        // when and then
        mockMvc.perform(post(api)
                .content(logoutRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName(value = "LogoutRequestDto 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. email: {0}")
    @ValueSource(strings = {"notEmailFormat"})
    @NullAndEmptySource
    public void validateLogoutRequestDto(String email) throws Exception {
        // given
        String api = "/auth/logout";
        String logoutRequestDto = objectMapper.writeValueAsString(
                LogoutRequestDto.builder()
                        .email(email)
                        .build());

        // when and then
        mockMvc.perform(post(api)
                .content(logoutRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
