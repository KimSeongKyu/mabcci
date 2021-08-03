package com.mabcci.domain.member.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.dto.MemberJoinRequest;
import com.mabcci.domain.member.dto.MemberDeleteRequestDto;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;

import static com.mabcci.domain.member.domain.Gender.MALE;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    private static final HashSet<String> CATEGORIES = new HashSet<>(Arrays.asList("categoryName"));

    @Autowired private ObjectMapper objectMapper;
    @Autowired private MockMvc mvc;

    @MockBean private MemberJoinService memberJoinService;
    @MockBean private MemberFindService memberFindService;
    @MockBean private MemberUpdateService memberUpdateService;
    @MockBean private MemberDeleteService memberDeleteService;

    @InjectMocks private MemberController memberController;

    @DisplayName("MemberRestController join 메서드 테스트")
    @Test
    public void join_test() throws Exception {
        final MemberResponseDto memberResponseDto = new MemberResponseDto(MEMBER);
        final MemberJoinRequest memberJoinRequest = new MemberJoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, MALE, CATEGORIES);
        final String joinRequestDtoString = objectMapper.writeValueAsString(memberJoinRequest);
        given(memberJoinService.join(any(), any())).willReturn(memberResponseDto);

        mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(joinRequestDtoString))
                .andExpect(status().isOk());
    }


    @DisplayName("MemberRestController update 메서드 테스트")
    @Test
    public void update_test() throws Exception {
        final MemberResponseDto memberResponseDto = new MemberResponseDto(MEMBER);
        final MemberUpdateRequestDto updateRequestDto = new MemberUpdateRequestDto(NICKNAME, MALE);
        final String updateRequestDtoString = objectMapper.writeValueAsString(updateRequestDto);
        final String memberResponseDtoString = objectMapper.writeValueAsString(memberResponseDto);

        given(memberUpdateService.update(any())).willReturn(memberResponseDto);

        mvc.perform(put("/api/members/" + NICKNAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequestDtoString))
                .andExpect(status().isOk())
                .andExpect(content().json(memberResponseDtoString));
    }

    @DisplayName("MemberRestController delete 메서드 테스트")
    @Test
    public void delete_test() throws Exception {
        final MemberDeleteRequestDto memberDeleteRequestDto = new MemberDeleteRequestDto(NICKNAME, PASSWORD);
        final String memberDeleteRequestDtoString = objectMapper.writeValueAsString(memberDeleteRequestDto);

        doNothing().when(memberDeleteService).delete(any(), any());

        mvc.perform(delete("/api/members/" + NICKNAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberDeleteRequestDtoString))
                .andExpect(status().isOk());
    }
}