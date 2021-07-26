package com.mabcci.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.auth.dto.LogoutRequestDto;
import com.mabcci.auth.exception.NotLoginMemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthControllerAdvice.class)
public class AuthControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthController authController;

    @DisplayName(value = "로그인하지 않은 유저가 로그아웃할 경우 에러 발생 테스트")
    @Test
    public void NotLoginMemberTryToLogoutThrowExceptionTest() throws Exception {
        // given
        String api = "/auth/logout";
        String email = "example@example.com";
        String logoutRequestDto = objectMapper.writeValueAsString(
                LogoutRequestDto.builder()
                        .email(email)
                        .build());

        // when
        doThrow(NotLoginMemberException.class).when(authController).logout(any());

        // then
        mockMvc.perform(post(api)
                .content(logoutRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
