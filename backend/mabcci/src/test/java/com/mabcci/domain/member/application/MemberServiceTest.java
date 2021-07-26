package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

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

    @DisplayName("MemberService 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberService).isNotNull(),
                () -> assertThat(memberService).isExactlyInstanceOf(MemberService.class)
        );
    }

    @DisplayName("MemberService save 기능 테스트")
    @Test
    void save_test() {
        // given
        given(memberRepository.save(any())).willReturn(member);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        then(memberRepository).should(times(1)).save(any());
        assertThat(savedMember.nickname()).isEqualTo(member.nickname());
    }

    @DisplayName("MemberService findByNickname 기능 테스트")
    @Test
    void findByNickname_test() {
        // given
        given(memberRepository.findByNickname("sample")).willReturn(Optional.ofNullable(member));

        // when
        Optional<Member> findMember = memberRepository.findByNickname("sample");

        // then
        then(memberRepository).should(times(1)).findByNickname(any());
        assertThat(findMember.isPresent()).isTrue();
    }

    @DisplayName("MemberService findAll 기능 테스트")
    @Test
    void findAll_test() {
        // given
        List<Member> memberList = new ArrayList<>(Arrays.asList(member));
        given(memberRepository.findAll()).willReturn(memberList);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        then(memberRepository).should(times(1)).findAll();
        assertThat(members.size()).isEqualTo(1);
    }

}