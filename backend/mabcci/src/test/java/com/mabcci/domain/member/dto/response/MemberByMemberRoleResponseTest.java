package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.application.MemberFindServiceTest.CATEGORY_NAME;
import static com.mabcci.domain.member.domain.MemberTest.*;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberByMemberRoleResponseTest {

    private Category category;
    private Member member;

    @BeforeEach
    void setUp() {
        category = new Category(CATEGORY_NAME);
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
        final MemberByMemberRoleResponse response = MemberByMemberRoleResponse
                .createMemberByMemberRoleResponse(member);

        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response).isExactlyInstanceOf(MemberByMemberRoleResponse.class)
        );
    }

    @DisplayName("MemberByMemberRoleResponse 인스턴스 getter 기능 테스트")
    @Test
    void getter_test() {
        final MemberCategory memberCategory = new MemberCategory(member, category);
        member.addMemberCategory(memberCategory);
        final MemberByMemberRoleResponse response = MemberByMemberRoleResponse
                .createMemberByMemberRoleResponse(member);

        assertAll(
                () -> assertThat(response.getNickName()).isEqualTo(member.nickname()),
                () -> assertThat(response.getPicture()).isEqualTo(member.picture()),
                () -> assertThat(response.getCategories()).isEqualTo(member.memberCategories())
        );
    }
}