package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.global.common.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.*;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberDeleteServiceTest {

    @Mock private MemberRepository memberRepository;
    @InjectMocks private MemberDeleteService memberDeleteService;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
    }

    @DisplayName("MemberDeleteService 인스턴스 delete() 기능 테스트")
    @Test
    void delete_test() {
        doReturn(Optional.ofNullable(member)).when(memberRepository).findByNicknameAndPassword(any(), any());
        doNothing().when(memberRepository).delete(any());

        memberDeleteService.delete(member.nickname(), PASSWORD);

        verify(memberRepository, times(1)).delete(any());
    }

}
