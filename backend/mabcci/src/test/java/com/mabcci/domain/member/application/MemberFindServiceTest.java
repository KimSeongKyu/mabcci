package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberListResponseDto;
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

    @Mock private MemberRepository memberRepository;
    @InjectMocks private MemberFindService memberFindService;

    @DisplayName("MemberFindService 인스턴스 findByNickname() 기능 테스트")
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
        final List<MemberListResponseDto> memberListResponseDtos = memberFindService.findAll();
        final MemberListResponseDto memberListResponseDto = memberListResponseDtos.get(0);

        assertAll(
                () -> assertThat(memberListResponseDtos.size()).isEqualTo(1),
                () -> assertThat(memberListResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberListResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberListResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberListResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

}