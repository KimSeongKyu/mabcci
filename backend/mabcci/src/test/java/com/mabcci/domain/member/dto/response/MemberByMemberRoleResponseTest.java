package com.mabcci.domain.member.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.dto.request.MemberJoinRequestTest.CATEGORIES;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberByMemberRoleResponseTest {

    @DisplayName("MemberByMemberRoleResponse 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final MemberByMemberRoleResponse response = new MemberByMemberRoleResponse();

        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response).isExactlyInstanceOf(MemberByMemberRoleResponse.class)
        );
    }

    @DisplayName("MemberByMemberRoleResponse 인스턴스 정적 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_method_test() {
        final String PICTURE = "picture 경로";
        final MemberByMemberRoleResponse response = MemberByMemberRoleResponse
                .createMemberByMemberRoleResponse(NICKNAME, PICTURE, CATEGORIES);

        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response).isExactlyInstanceOf(MemberByMemberRoleResponse.class)
        );
    }

    @DisplayName("MemberByMemberRoleResponse 인스턴스 getter 기능 테스트")
    @Test
    void getter_test() {
        final String PICTURE = "picture 경로";
        final MemberByMemberRoleResponse response = MemberByMemberRoleResponse
                .createMemberByMemberRoleResponse(NICKNAME, PICTURE, CATEGORIES);

        assertAll(
                () -> assertThat(response.getNickName()).isEqualTo(NICKNAME),
                () -> assertThat(response.getPicture()).isEqualTo(PICTURE),
                () -> assertThat(response.getCategories()).isEqualTo(CATEGORIES)
        );
    }
}