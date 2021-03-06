package com.mabcci.domain.follow.dto;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FollowRequestTest {

    @DisplayName("FollowSaveRequest 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final FollowRequest followRequest = new FollowRequest();

        assertAll(
                () -> assertThat(followRequest).isNotNull(),
                () -> assertThat(followRequest).isExactlyInstanceOf(FollowRequest.class)
        );
    }

    @DisplayName("FollowSaveRequest 인스턴스 정적 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_method_test() {
        final Nickname following = Nickname.of("following");
        final Nickname follower = Nickname.of("follower");
        final FollowRequest followRequest = new FollowRequest(following, follower);

        assertAll(
                () -> assertThat(followRequest).isNotNull(),
                () -> assertThat(followRequest).isExactlyInstanceOf(FollowRequest.class)
        );
    }

    @DisplayName("FollowSaveRequest 인스턴스 getter 기능 테스트")
    @Test
    void getter_test() {
        final Nickname following = Nickname.of("following");
        final Nickname follower = Nickname.of("follower");
        final FollowRequest followRequest = new FollowRequest(following, follower);

        assertAll(
                () -> assertThat(followRequest.following()).isEqualTo(following),
                () -> assertThat(followRequest.follower()).isEqualTo(follower)
        );
    }

}