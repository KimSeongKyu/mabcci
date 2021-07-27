package com.mabcci.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.auth.dto.LogoutRequestDto;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthControllerAdvice.class)
public class AuthControllerAdviceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @DisplayName(value = "로그인하지 않은 유저가 로그아웃할 경우 NotLoginMemberException 발생 테스트")
    @Test
    public void NotLoginMemberTryToLogoutThrowExceptionTest() throws Exception {
        // given
        String api = "/auth/logout";
        String email = "example@example.com";
        String logoutRequestDto = objectMapper.writeValueAsString(
                LogoutRequestDto.builder()
                        .email(email)
                        .build());
        NotLoginMemberException notLoginMemberException = new NotLoginMemberException(email);

        given(authController.logout(any())).willThrow(notLoginMemberException);

        // when and then
        mockMvc.perform(post(api)
                .content(logoutRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(notLoginMemberException.toString()));
    }
}
