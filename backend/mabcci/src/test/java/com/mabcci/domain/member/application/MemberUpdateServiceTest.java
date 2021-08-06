package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.*;
import com.mabcci.domain.member.dto.response.MemberByNickNameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberSpecsTest.*;
import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberUpdateServiceTest {

    @Mock private MemberRepository memberRepository;
    @InjectMocks private MemberUpdateService memberUpdateService;

    private MemberSpecs memberSpecs;
    private Member member;

    @BeforeEach
    void setUp() {
        memberSpecs = MemberSpecs.Builder()
                .weight(WEIGHT)
                .height(HEIGHT)
                .footSize(FOOT_SIZE)
                .form(BodyType.TRIANGLE)
                .build();

        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .memberSpecs(memberSpecs)
                .build();
    }



    @DisplayName("MemberUpdateService 인스턴스 update() 기능 테스트")
    @Test
    void update_test() {
        given(memberRepository.findByNickName(any())).willReturn(Optional.ofNullable(member));
        final Member findMember = memberUpdateService.update(NICKNAME, Gender.MAN);
        final MemberByNickNameResponse memberByNickNameResponse = new MemberByNickNameResponse(findMember);

        then(memberRepository).should(times(1)).findByNickName(any());
        assertAll(
                () -> assertThat(memberByNickNameResponse.getNickname()).isEqualTo(member.nickname()),
                () -> assertThat(memberByNickNameResponse.getGender()).isEqualTo(Gender.MAN)
        );
    }
}
