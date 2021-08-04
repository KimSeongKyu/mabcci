package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock private FollowRepository followRepository;
    @Mock private MemberRepository memberRepository;
    @InjectMocks private FollowService followService;

    private Member following;
    private Member follower;

    @BeforeEach
    void setUp() {
        following = member(Email.of("following@email.com"), PASSWORD, Nickname.of("following"), Phone.of("010-1234-5678"));
        follower = member(Email.of("follower@email.com"), PASSWORD, Nickname.of("follower"), Phone.of("010-5678-1234"));
    }

    @DisplayName("FollowService 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final FollowService followService = new FollowService(followRepository, memberRepository);

        assertAll(
                () -> assertThat(followService).isNotNull(),
                () -> assertThat(followService).isExactlyInstanceOf(FollowService.class)
        );
    }

    @DisplayName("FollowService 인스턴스 save() 기능 테스트")
    @Test
    void save_test() {
        final Follow follow = Follow.Builder().following(following).follower(follower).build();
        ReflectionTestUtils.setField(follow, "id", 1L);

        given(followRepository.save(any())).willReturn(follow);
        given(memberRepository.findByNickname(following.nickname())).willReturn(Optional.ofNullable(following));
        given(memberRepository.findByNickname(follower.nickname())).willReturn(Optional.ofNullable(follower));

        final Long actual = followService.save(following.nickname(), follower.nickname());

        then(followRepository).should(times(1)).save(any());
        then(memberRepository).should(times(2)).findByNickname(any());
        assertThat(actual).isEqualTo(1L);
    }

    private static Member member(Email email, Password password, Nickname nickname, Phone phone) {
        return Member.Builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

}