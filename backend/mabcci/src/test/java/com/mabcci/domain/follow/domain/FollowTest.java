package com.mabcci.domain.follow.domain;

import com.mabcci.domain.member.domain.BodyType;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.member.domain.Gender.MALE;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.domain.member.domain.MemberSpecsTest.*;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FollowTest {

    @DisplayName("Follow 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Follow follow = new Follow();

        assertAll(
                () -> assertThat(follow).isNotNull(),
                () -> assertThat(follow).isExactlyInstanceOf(Follow.class)
        );
    }

    @DisplayName("Follow 인스턴스 빌더를 이용한 생성 테스트")
    @Test
    void builder_test() {
        final Member following = member();
        final Member follower = member();
        ReflectionTestUtils.setField(follower, "id", 1L);
        ReflectionTestUtils.setField(following, "id", 2L);

        final Follow follow = follow(following, follower);
        assertAll(
                () -> assertThat(follow).isNotNull(),
                () -> assertThat(follow).isExactlyInstanceOf(Follow.class)
        );
    }

    private Follow follow(Member following, Member follower) {
        return Follow.Builder()
                .following(following)
                .follower(follower)
                .build();
    }

    private Member member() {
        return Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MALE)
                .role(USER)
                .memberSpecs(MemberSpecs.Builder()
                        .height(HEIGHT)
                        .weight(WEIGHT)
                        .footSize(FOOT_SIZE)
                        .form(BodyType.TRIANGLE)
                        .build())
                .build();
    }
}