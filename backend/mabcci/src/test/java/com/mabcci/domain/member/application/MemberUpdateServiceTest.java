package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberUpdateServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberUpdateService memberUpdateService;

    @DisplayName("MemberUpdateService 인스턴스 update() 기능 테스트")
    @Test
    void update_test() {
        given(memberRepository.findByNickname(any())).willReturn(Optional.ofNullable(MEMBER));
        Member member = memberUpdateService.update(NICKNAME, Gender.MALE);
        final MemberResponseDto memberResponseDto = new MemberResponseDto(member);

        then(memberRepository).should(times(1)).findByNickname(any());
        assertAll(
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(Gender.MALE)
        );
    }
}
