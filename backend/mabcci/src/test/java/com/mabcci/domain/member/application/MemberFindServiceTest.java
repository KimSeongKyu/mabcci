package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberFindServiceTest {

    private static final HashSet<String> CATEGORIES = new HashSet<>(Arrays.asList("categoryName"));

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberFindService memberFindService;

    @DisplayName("MemberService 인스턴스 findByNickname() 기능 테스트")
    @Test
    void findByNickname_test() {
        given(memberRepository.findByNickname(any())).willReturn(Optional.ofNullable(MEMBER));
        final MemberResponseDto memberResponseDto = memberFindService.findByNickName(MEMBER.nickname());

        assertAll(
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

    @DisplayName("MemberService 인스턴스 findAll() 기능 테스트")
    @Test
    void findAll_test() {
        final List list = new ArrayList(Arrays.asList(MEMBER));
        given(memberRepository.findAll()).willReturn((list));
        final List<MemberResponseDto> memberResponseDtos = memberFindService.findAll();
        final MemberResponseDto memberResponseDto = memberResponseDtos.get(0);

        assertAll(
                () -> assertThat(memberResponseDtos.size()).isEqualTo(1),
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

}