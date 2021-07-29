package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.model.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @InjectMocks
    private MemberJoiningService memberJoiningService;

    @DisplayName("MemberService 인스턴스 join() 기능 테스트")
    @Test
    void join_test() {
        given(memberRepository.save(any())).willReturn(MEMBER);
        final MemberResponseDto memberResponseDto = memberJoiningService.join(MEMBER);

        assertAll(
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

    @DisplayName("MemberService 인스턴스 findByNickname() 기능 테스트")
    @Test
    void findByNickname_test() {
        given(memberRepository.findByNickname(any())).willReturn(Optional.ofNullable(MEMBER));
        final MemberResponseDto memberResponseDto = memberService.findByNickName(MEMBER.nickname());

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
        final List<MemberResponseDto> memberResponseDtos = memberService.findAll();
        final MemberResponseDto memberResponseDto = memberResponseDtos.get(0);

        assertAll(
                () -> assertThat(memberResponseDtos.size()).isEqualTo(1),
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

    @DisplayName("MemberService 인스턴스 delete() 기능 테스트")
    @Test
    void delete_test() {
        doReturn(Optional.ofNullable(MEMBER)).when(memberRepository).findByNicknameAndPassword(any(), any());
        doNothing().when(memberRepository).delete(any());

        memberService.delete(MEMBER.nickname(), Password.of("password"));

        verify(memberRepository, times(1)).delete(any());
    }


}