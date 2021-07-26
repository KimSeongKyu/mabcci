package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberService;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.dto.MemberResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email("sample@email.com")
                .password("validPassword")
                .nickname("sample")
                .phone("010-1234-5678")
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

    @DisplayName("MemberRestController findAll 메서드 테스트")
    @Test
    public void findAll_test() throws Exception {
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        given(memberService.findAll()).willReturn(Collections.singletonList(memberResponseDto));

        mvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("members"));
                //.andExpect(model().attribute("members", contains(memberResponseDto)));
    }
}