package com.mabcci.auth.controller;

import com.mabcci.auth.dto.LogoutRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName(value = "로그아웃 API 성공 테스트")
    @Test
    public void logoutSuccessTest() throws Exception {
        // given
        String api = "/auth/logout";
        String email = "example@example.com";
        LogoutRequestDto logoutRequestDto = new LogoutRequestDto(email);

        // when
        mockMvc.perform(post(api, logoutRequestDto))
                .andExpect(status().isOk())
                .andExpect(model().attribute("email", email));
    }
}
