package com.mabcci.domain.member.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.member.application.MemberService;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.dto.JoinRequestDto;
import com.mabcci.domain.member.dto.MemberResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    private Member member;

    private static final String EMAIL = "sample@email.com";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "nickname";
    private static final String PHONE = "01012345678";
    private static final Gender GENDER = Gender.MALE;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(GENDER)
                .role(MemberRole.USER)
                .build();
    }


    @DisplayName("MemberRestController join 메서드 테스트")
    @Test
    public void join_test() throws Exception {
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        given(memberService.join(any())).willReturn(memberResponseDto);

        JoinRequestDto joinRequestDto = new JoinRequestDto(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);
        String joinRequestDtoString = objectMapper.writeValueAsString(joinRequestDto);

        mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(joinRequestDtoString))
                .andExpect(status().isOk());
    }

    @DisplayName("MemberRestController findByNickname 메서드 테스트")
    @Test
    public void findByNickname_test() throws Exception {
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        given(memberService.findByNickName(any())).willReturn(memberResponseDto);
        String memberResponseDtoString = objectMapper.writeValueAsString(memberResponseDto);

        mvc.perform(get("/api/members/"+NICKNAME))
                .andExpect(status().isOk())
                .andExpect(content().json(memberResponseDtoString));
    }


    @DisplayName("MemberRestController findAll 메서드 테스트")
    @Test
    public void findAll_test() throws Exception {
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        List<MemberResponseDto> memberResponseDtos = Collections.singletonList(memberResponseDto);
        given(memberService.findAll()).willReturn(memberResponseDtos);

        String memberResponseDtosString = objectMapper.writeValueAsString(memberResponseDtos);

        mvc.perform(get("/api/members"))
                .andExpect(status().isOk())
                .andExpect(content().json(memberResponseDtosString));
    }
}